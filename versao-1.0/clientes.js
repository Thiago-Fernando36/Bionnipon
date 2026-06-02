const postsContainer = document.querySelector("#listaClientes");
const BASE_URL = "http://localhost:3000/client";


async function loadClients() {
  try {
    const apiResponse = await fetch(BASE_URL);
    if (!apiResponse.ok) {
      throw new Error(`API retornou status ${apiResponse.status}`);
    }

    const parsedResponse = await apiResponse.json();
    console.log("parsedResponse", parsedResponse);

    postsContainer.innerHTML = "";

    parsedResponse.forEach((post) => {
      const div = document.createElement("div");
      div.className = "cliente";

      const title = document.createElement("h2");
      title.textContent = post.nome || "Sem nome";

      const body = document.createElement("p");
      body.textContent = post.endereco || "Endereço não informado";

      const link = document.createElement("a");
      if (post.telefone) {
        link.href = `tel:${post.telefone}`;
        link.textContent = post.telefone;
      } else {
        link.textContent = "Telefone não informado";
        link.removeAttribute("href");
      }

      div.appendChild(title);
      div.appendChild(body);
      div.appendChild(link);
      postsContainer.appendChild(div);
    });
  } catch (error) {
    console.error("Erro ao carregar clientes:", error);
    postsContainer.innerHTML = '<p class="error">Não foi possível carregar clientes. Verifique o servidor e CORS.</p>';
  }
}


document.addEventListener("DOMContentLoaded", loadClients);