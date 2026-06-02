// Adapte BASE_URL se necessário
const daysTag = document.querySelector(".days"),
  currentDate = document.querySelector(".current-date"),
  prevNextIcon = document.querySelectorAll(".icons span");

let date = new Date(),
  currYear = date.getFullYear(),
  currMonth = date.getMonth();

const months = [
  "Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho",
  "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"
];

const postsContainer = document.querySelector("#tarefaDia"); // container onde os dados serão renderizados

const renderCalendar = () => {
  let firstDayofMonth = new Date(currYear, currMonth, 1).getDay(),
    lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate(),
    lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay(),
    lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();

  let liTag = "";

  // dias do mês anterior (inativos)
  for (let i = firstDayofMonth; i > 0; i--) {
    liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
  }

  // dias do mês atual
  for (let i = 1; i <= lastDateofMonth; i++) {
    let isToday = i === date.getDate() && currMonth === new Date().getMonth()
      && currYear === new Date().getFullYear() ? "active" : "";
    // clique no dia chama abrirDiv
    liTag += `<li class="${isToday}" onclick="abrirDiv(${i}, ${currMonth}, ${currYear})">${i}</li>`;
  }

  // dias do próximo mês (inativos)
  for (let i = lastDayofMonth; i < 6; i++) {
    liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
  }

  currentDate.innerText = `${months[currMonth]} ${currYear}`;
  daysTag.innerHTML = liTag;
};

renderCalendar();

prevNextIcon.forEach(icon => {
  icon.addEventListener("click", () => {
    currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;
    if (currMonth < 0 || currMonth > 11) {
      date = new Date(currYear, currMonth, new Date().getDate());
      currYear = date.getFullYear();
      currMonth = date.getMonth();
    } else {
      date = new Date();
    }
    renderCalendar();
  });
});

function fecharDiv() {
  const tarefaDiaEl = document.getElementById('tarefaDia');
  const semDivEl = document.getElementById('semDiv');
  if (tarefaDiaEl) tarefaDiaEl.style.display = 'none';
  if (semDivEl) semDivEl.style.display = 'none';
}

/**
 * Busca serviços e filtra por um intervalo de data no formato ISO (YYYY-MM-DDThh:mm:ss.sssZ).
 * Exibe os dados do campo `cliente` (objeto) presente em cada serviço.
 * A data é exibida em formato brasileiro (DD/MM/YYYY) e a hora em um campo separado abaixo.
 */
async function fetchAndRenderClients(startIso, endIso) {
  const BASE_URL = "http://localhost:3000/servico";

  try {
    const apiResponse = await fetch(BASE_URL);
    if (!apiResponse.ok) throw new Error(`Erro na requisição: ${apiResponse.status} ${apiResponse.statusText}`);
    let parsedResponse = await apiResponse.json();
    console.log("parsedResponse", parsedResponse);

    // Caso o backend retorne um único objeto em vez de array, convertê-lo para array
    if (!Array.isArray(parsedResponse)) {
      parsedResponse = [parsedResponse];
    }

    if (!postsContainer) {
      console.warn("#tarefaDia não encontrado para renderizar itens.");
      return;
    }

    // Limpa antes de renderizar (mantém um título com a data)
    postsContainer.innerHTML = `<strong>Tarefa diária: ${startIso ? startIso.substring(0,10) : 'Todas'}</strong><div class="itens"></div>`;
    const itensContainer = postsContainer.querySelector('.itens');

    // Converte parâmetros de intervalo para Date (se fornecidos)
    const startDate = startIso ? new Date(startIso) : null;
    const endDate = endIso ? new Date(endIso) : null;

    // Filtra os itens usando o campo de data ISO (p.data)
    let items = parsedResponse;
    if (startDate && endDate) {
      items = parsedResponse.filter(p => {
        const dateStr = p.data; // seu backend usa `data` em ISO
        if (!dateStr) return false;
        const itemDate = new Date(dateStr);
        if (isNaN(itemDate)) return false;
        return itemDate >= startDate && itemDate <= endDate;
      });
    }

    if (!items || items.length === 0) {
      itensContainer.innerHTML = "<p>Não há tarefas para esta data.</p>";
      return;
    }

    items.forEach((post) => {
      const div = document.createElement("div");
      div.className = "tarefa-item";

      // Dados do serviço
      const servicoTitle = document.createElement("h3");
      servicoTitle.innerText = post.tipo ?? "Serviço sem tipo";

      // Formatação da data e hora em pt-BR
      const dateStr = post.data;
      let displayDate = "Data do serviço: não informada";
      let displayTime = "Hora não informada";
      if (dateStr) {
        const itemDate = new Date(dateStr);
        if (!isNaN(itemDate)) {
          // Data no formato brasileiro (DD/MM/YYYY)
          displayDate = `Data do serviço: ${itemDate.toLocaleDateString('pt-BR')}`;
          // Hora separada — exibe HH:MM:SS (pode ajustar para HH:MM se preferir)
          displayTime = `Hora do serviço: ${itemDate.toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit'})}`;
        }
      }
      const servicoDate = document.createElement("p");
      servicoDate.className = "data-servico";
      servicoDate.innerText = displayDate;

      const servicoTime = document.createElement("p");
      servicoTime.className = "hora-servico";
      servicoTime.innerText = displayTime;

      // Dados do cliente (objeto)
      const cliente = post.cliente ?? {};
      const clienteContainer = document.createElement("div");
      clienteContainer.className = "cliente-info";

      const clienteNome = document.createElement("h4");
      clienteNome.innerText = cliente.nome ?? "Cliente sem nome";

      const clienteTelefone = document.createElement("a");
      if (cliente.telefone) {
        // Formata telefone simples para tel:
        clienteTelefone.href = `tel:${cliente.telefone}`;
        clienteTelefone.innerText = cliente.telefone;
      } else {
        clienteTelefone.innerText = "Telefone não informado";
        clienteTelefone.removeAttribute("href");
      }

      const clienteEndereco = document.createElement("p");
      clienteEndereco.innerText = cliente.endereco ?? "Endereço não informado";

      // Monta elementos
      clienteContainer.appendChild(clienteNome);
      clienteContainer.appendChild(clienteTelefone);
      clienteContainer.appendChild(clienteEndereco);

      // Link / id do serviço/cliente se quiser navegar
      const meta = document.createElement("p");
      meta.className = "meta";
      meta.innerText = post.id ? `Serviço ID: ${post.id}` : "";

      div.appendChild(servicoTitle);
      div.appendChild(servicoDate);
      // hora em novo campo abaixo da data
      div.appendChild(servicoTime);
      div.appendChild(clienteContainer);
      div.appendChild(meta);

      itensContainer.appendChild(div);
    });
  } catch (err) {
    console.error("Falha ao carregar dados:", err);
    if (postsContainer) postsContainer.innerHTML = `<p>Erro ao carregar dados: ${err.message}</p>`;
  }
}

/**
 * Ao abrir a div para um dia específico, criamos um intervalo que cobre
 * desde o início do dia até o fim do dia e passamos esses ISO strings para o filtro.
 */
function abrirDiv(dia, mes, ano) {

  const tarefaDiaEl = document.getElementById('tarefaDia');
  const semDivEl = document.getElementById('semDiv');
  if (tarefaDiaEl) tarefaDiaEl.style.display = 'block';
  if (semDivEl) semDivEl.style.display = 'block';

  // Garante formatação com dois dígitos
  const diaFormatado = dia.toString().padStart(2, '0');
  const mesFormatado = (mes + 1).toString().padStart(2, '0');

  // Cria intervalo local e converte para ISO (UTC)
  const startIso = new Date(ano, mes, dia, 0, 0, 0, 0).toISOString();
  const endIso = new Date(ano, mes, dia, 23, 59, 59, 999).toISOString();

  console.log("Abrindo div para data (ISO intervalo):", startIso, "->", endIso);

  fetchAndRenderClients(startIso, endIso);
}

// Se desejar abrir a div no carregamento para o dia atual, descomente:
// document.addEventListener('DOMContentLoaded', () => abrirDiv(new Date().getDate(), new Date().getMonth(), new Date().getFullYear()));