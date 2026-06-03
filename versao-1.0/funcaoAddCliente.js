// funcaoAddCliente.js
// Código robusto: espera DOMContentLoaded, verifica elementos e faz POST /client
(() => {
  const API = 'http://localhost:3000';

  function notify(text, type = 'log') {
    // substitua por UI real se quiser — por enquanto, console
    if (type === 'error') console.error(text);
    else console.log(text);
  }

  async function postJSON(path, data) {
    try {
      const resp = await fetch(API + path, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(data),
      });
      const text = await resp.text();
      let body = text;
      try { body = JSON.parse(text); } catch (e) { /* não JSON */ }
      if (!resp.ok) {
        const msg = `HTTP ${resp.status} ${resp.statusText} - ${JSON.stringify(body)}`;
        const err = new Error(msg);
        err.status = resp.status;
        err.body = body;
        throw err;
      }
      return body;
    } catch (err) {
      throw err;
    }
  }

  function safeGet(id) {
    const el = document.getElementById(id);
    if (!el) {
      notify(`Elemento com id "${id}" não encontrado no DOM. Verifique o HTML ou o momento de carregamento.`, 'error');
    }
    return el;
  }

  document.addEventListener('DOMContentLoaded', () => {
    const clientForm = safeGet('clientForm');
    if (!clientForm) return;

    const notification = safeGet('notification'); // pode ser null, é só uma tentativa

    clientForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const fd = new FormData(clientForm);
      const payload = {
        nome: fd.get('nome'),
        telefone: fd.get('telefone'),
        endereco: fd.get('endereco') || null
      };

      // validação local mínima
      if (!payload.nome || !payload.telefone) {
        const msg = 'nome e telefone são obrigatórios';
        if (notification) notification.innerText = msg;
        return notify(msg, 'error');
      }

      try {
        const result = await postJSON('/client', payload);
        notify('Cliente criado com sucesso: ' + JSON.stringify(result));
        clientForm.reset();
        // opcional: disparar um evento para atualizar listas
        window.dispatchEvent(new Event('clientes-atualizados'));
      } catch (err) {
        notify('Erro ao criar cliente: ' + (err.message || err), 'error');
        if (notification) notification.innerText = 'Erro: ' + (err.body?.error || err.message || 'ver console');
      }
    });
  });
})();