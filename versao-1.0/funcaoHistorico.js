async function ExibirHistorico(data) {
  const container = document.getElementById('listaHistorico');
  if (!container) return;

  container.innerHTML = '<p>Carregando...</p>';

  // envia a data selecionada como query param (formato YYYY-MM-DD)
  const url = `http://localhost:3000/servico${data ? `?data=${encodeURIComponent(data)}` : ''}`;

  try {
    const res = await fetch(url, { method: 'GET' });
    if (!res.ok) {
      throw new Error(`Erro no servidor: ${res.status} ${res.statusText}`);
    }
    const json = await res.json();

    // json esperado: array de registros no formato informado
    renderizarLista(json, container, data);

  } catch (err) {
    console.error(err);
    container.innerHTML = `<p class="erro">Não foi possível carregar o histórico: ${escapeHtml(err.message)}</p>`;
  }
}

function renderizarLista(data, container, filterDate) {
  container.innerHTML = ''; // limpa

  if (!data || !Array.isArray(data) || data.length === 0) {
    container.innerHTML = '<p>Nenhum cliente atendido nessa data.</p>';
    return;
  }

  // Se foi passada uma data para filtrar (YYYY-MM-DD), filtra os registros que têm a mesma data local
  let lista = data;
  if (filterDate) {
    lista = data.filter(item => {
      if (!item || !item.data) return false;
      const d = new Date(item.data);
      if (isNaN(d)) return false;
      // monta YYYY-MM-DD no timezone local para comparar com o input date (que normalmente é local)
      const yyyy = d.getFullYear();
      const mm = String(d.getMonth() + 1).padStart(2, '0');
      const dd = String(d.getDate()).padStart(2, '0');
      const itemDate = `${yyyy}-${mm}-${dd}`;
      return itemDate === filterDate;
    });
  }

  if (!Array.isArray(lista) || lista.length === 0) {
    container.innerHTML = '<p>Nenhum cliente atendido nessa data.</p>';
    return;
  }

  const ul = document.createElement('ul');
  ul.className = 'historico-lista';

  lista.forEach(item => {
    const li = document.createElement('li');
    li.className = 'historico-item';

    // campos do novo JSON
    const id = item.id ?? '—';
    const tipo = item.tipo ?? '—';
    const dataISO = item.data ?? null;
    const prazo = (item.prazo !== undefined && item.prazo !== null) ? `${item.prazo} dias` : '—';
    const garantia = (item.garantia !== undefined && item.garantia !== null) ? `${item.garantia} dias` : '—';

    const cliente = item.cliente || {};
    const nomeCliente = cliente.nome || '—';
    const telefone = cliente.telefone || '—';
    const endereco = cliente.endereco || '—';

    const dataFormatada = dataISO ? formatDateTimeLocal(dataISO) : '—';

    li.innerHTML = `
      <div class="item-header">
        <strong class="item-nome">${escapeHtml(nomeCliente)}<br/></strong>
        <span class="item-tipo">Tipo: ${escapeHtml(tipo)}<br/></span>
        <span class="item-data">${escapeHtml(dataFormatada)}<br/></span>
      </div>
      <div class="item-body">
        <div class="item-detalhes">
          <span class="item-prazo">Prazo: ${escapeHtml(prazo)}<br/></span>
          <span class="item-garantia">Garantia: ${escapeHtml(garantia)}<br/></span>
        </div>
        <div class="item-contato">
          <span class="item-telefone">Tel: ${escapeHtml(telefone)}<br/></span>
          <span class="item-endereco">End: ${escapeHtml(endereco)}<br/></span>
        </div>
        <div class="item-meta">
          <small>ID registro: ${escapeHtml(String(id))}<br/></small>
        </div>
      </div>
    `;

    ul.appendChild(li);
  });

  container.appendChild(ul);
}

// formata data ISO para uma string legível (data local + hora local)
function formatDateTimeLocal(iso) {
  try {
    const d = new Date(iso);
    if (isNaN(d)) return iso;
    // usa o formato YYYY-MM-DD HH:MM (ajuste conforme preferir)
    const yyyy = d.getFullYear();
    const mm = String(d.getMonth() + 1).padStart(2, '0');
    const dd = String(d.getDate()).padStart(2, '0');
    const hh = String(d.getHours()).padStart(2, '0');
    const min = String(d.getMinutes()).padStart(2, '0');
    return `${yyyy}-${mm}-${dd} ${hh}:${min}`;
  } catch (e) {
    return iso;
  }
}

// escapar HTML e evitar injeções
function escapeHtml(str) {
  if (str === null || str === undefined) return '';
  return String(str)
    .replace(/&/g, '&amp;')
    .replace(/</g, '&lt;')
    .replace(/>/g, '&gt;')
    .replace(/"/g, '&quot;')
    .replace(/'/g, '&#039;');
}

// Inicialização: seta data atual e liga eventos
document.addEventListener('DOMContentLoaded', () => {
  const inputData = document.getElementById('dataHistorico');
  const btn = document.getElementById('btnConsultar');

  if (!inputData) return;

  // Define hoje como padrão
  const hoje = new Date();
  const yyyy = hoje.getFullYear();
  const mm = String(hoje.getMonth() + 1).padStart(2, '0');
  const dd = String(hoje.getDate()).padStart(2, '0');
  inputData.value = `${yyyy}-${mm}-${dd}`;

  // Consultar ao carregar a página
  ExibirHistorico(inputData.value);

  // Consultar ao clicar no botão
  if (btn) {
    btn.addEventListener('click', () => {
      if (inputData.value) ExibirHistorico(inputData.value);
    });
  }

  // Também consultar automaticamente quando a data mudar
  inputData.addEventListener('change', () => {
    if (inputData.value) ExibirHistorico(inputData.value);
  });
});