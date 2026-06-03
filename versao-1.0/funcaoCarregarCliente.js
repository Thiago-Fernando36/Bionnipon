// app.js (diagnóstico e tratamento de erros)
// Busca http://localhost:3000/client e apresenta nome, telefone e endereço.
// Adicionado: logs, timeout com AbortController, mensagens de erro mais claras.

const API_URL = 'http://localhost:3000/client';

const statusEl = document.getElementById('status');
const appEl = document.getElementById('app');
const detailEl = document.getElementById('detail');
const detailContent = document.getElementById('detailContent');
const closeDetailBtn = document.getElementById('closeDetail');
const viewSelect = document.getElementById('view');
const searchInput = document.getElementById('search');
const reloadBtn = document.getElementById('reload');

let clientsRaw = [];
let clients = [];
let filtered = [];
let currentView = 'cards';

document.addEventListener('DOMContentLoaded', () => {
  attachListeners();
  loadClients();
});

function attachListeners() {
  viewSelect.addEventListener('change', () => {
    currentView = viewSelect.value;
    render();
  });

  searchInput.addEventListener('input', () => {
    const q = searchInput.value.trim().toLowerCase();
    applyFilter(q);
    render();
  });

  reloadBtn.addEventListener('click', () => {
    loadClients();
  });

  closeDetailBtn.addEventListener('click', () => hideDetail());

  appEl.addEventListener('click', (e) => {
    const item = e.target.closest('[data-client-id]');
    if (!item) return;
    const id = item.getAttribute('data-client-id');
    const c = clients.find(x => String(x.id) === String(id));
    if (c) showDetail(c);
  });
}

async function loadClients() {
  showStatus('Carregando...');
  console.log('[app] iniciando requisição para', API_URL);

  // Timeout: aborta após 8s
  const controller = new AbortController();
  const timeoutMs = 8000;
  const timeout = setTimeout(() => {
    controller.abort();
  }, timeoutMs);

  try {
    const res = await fetch(API_URL, {
      headers: { 'Accept': 'application/json' },
      signal: controller.signal,
      // mode: 'cors' // opcional; browser decide automaticamente
    });

    clearTimeout(timeout);

    console.log('[app] resposta recebida', res);
    console.log('[app] status', res.status, res.statusText);

    if (!res.ok) {
      // tenta ler corpo para mais informação
      let bodyText = '';
      try { bodyText = await res.text(); } catch (_) {}
      throw new Error(`HTTP ${res.status} ${res.statusText} - ${bodyText}`);
    }

    const data = await res.json().catch(err => {
      throw new Error('Resposta não é JSON válido: ' + err.message);
    });

    console.log('[app] JSON recebido', data);
    clientsRaw = normalizeToArray(data);
    clients = clientsRaw.map(mapToViewModel);
    filtered = clients.slice();
    showStatus(`Carregados ${clients.length} clientes.`);
    render();
  } catch (err) {
    clearTimeout(timeout);
    console.error('[app] erro ao buscar clientes:', err);

    if (err.name === 'AbortError') {
      showStatus('Erro: requisição abortada (timeout). Verifique se o servidor está respondendo.');
    } else {
      // Mensagens mais específicas para ajudar no diagnóstico
      const msg = String(err.message || err);
      if (msg.includes('Failed to fetch') || msg.includes('NetworkError')) {
        showStatus('Erro de rede: não foi possível conectar ao servidor. Verifique se http://localhost:3000 está rodando e se o endpoint /client existe.');
      } else if (msg.toLowerCase().includes('cors')) {
        showStatus('Erro CORS: o servidor não permite requisições desta origem. Verifique os headers Access-Control-Allow-Origin no servidor.');
      } else {
        showStatus('Erro ao carregar os dados: ' + msg);
      }
    }

    appEl.innerHTML = '';
  }
}

function normalizeToArray(data) {
  if (Array.isArray(data)) return data;
  if (data == null) return [];
  return [data];
}

function mapToViewModel(raw) {
  const id = raw.id ?? raw._id ?? '';
  const nome = firstNonEmpty(raw, ['name', 'nome', 'fullName', 'fullname']) || '—';
  let telefone = '';
  if (Array.isArray(raw.phones) && raw.phones.length) telefone = raw.phones.join(', ');
  else if (Array.isArray(raw.telefones) && raw.telefones.length) telefone = raw.telefones.join(', ');
  else telefone = raw.phone || raw.telefone || raw.mobile || '';

  let endereco = '';
  if (typeof raw.endereco === 'string' && raw.endereco.trim()) endereco = raw.endereco.trim();
  else if (raw.address && typeof raw.address === 'object') endereco = formatAddress(raw.address);
  else {
    const parts = [
      firstNonEmpty(raw, ['street', 'rua', 'logradouro']),
      firstNonEmpty(raw, ['number', 'numero']),
      firstNonEmpty(raw, ['neighborhood', 'bairro']),
      firstNonEmpty(raw, ['city', 'cidade']),
      firstNonEmpty(raw, ['state', 'estado']),
      firstNonEmpty(raw, ['zip', 'cep'])
    ].filter(Boolean);
    endereco = parts.join(', ');
  }

  return {
    id,
    nome,
    telefone: telefone || '—',
    endereco: endereco || '—',
    raw
  };
}

function firstNonEmpty(obj, keys) {
  for (const k of keys) {
    const v = obj?.[k];
    if (v !== undefined && v !== null && String(v).trim() !== '') return String(v).trim();
  }
  return '';
}

function formatAddress(addr) {
  const parts = [];
  if (addr.street || addr.rua || addr.logradouro) parts.push(firstNonEmpty(addr, ['street', 'rua', 'logradouro']));
  if (addr.number || addr.numero) parts.push(firstNonEmpty(addr, ['number', 'numero']));
  if (addr.neighborhood || addr.bairro) parts.push(firstNonEmpty(addr, ['neighborhood', 'bairro']));
  const cityState = [ firstNonEmpty(addr, ['city', 'cidade']), firstNonEmpty(addr, ['state', 'estado']) ].filter(Boolean).join(' - ');
  if (cityState) parts.push(cityState);
  if (addr.zip || addr.cep) parts.push(firstNonEmpty(addr, ['zip', 'cep']));
  return parts.join(', ');
}

function applyFilter(query) {
  if (!query) {
    filtered = clients.slice();
    return;
  }
  filtered = clients.filter(c => {
    return (c.nome || '').toLowerCase().includes(query) ||
           (c.telefone || '').toLowerCase().includes(query);
  });
}

function render() {
  appEl.className = 'grid ' + currentView;
  if (filtered.length === 0) {
    appEl.innerHTML = '<p class="small">Nenhum cliente encontrado.</p>';
    return;
  }

  if (currentView === 'table') renderTable(filtered);
  else if (currentView === 'list') renderList(filtered);
  else renderCards(filtered);
}

function renderCards(items) {
  const frag = document.createDocumentFragment();
  items.forEach(c => {
    const card = document.createElement('article');
    card.className = 'card';
    card.setAttribute('data-client-id', c.id);

    const title = document.createElement('h3'); title.textContent = c.nome;
    const tel = document.createElement('p'); tel.className = 'small'; tel.textContent = 'Telefone: ' + c.telefone;
    const addr = document.createElement('p'); addr.textContent = 'Endereço: ' + c.endereco;

    card.append(title, tel, addr);
    frag.appendChild(card);
  });
  appEl.innerHTML = '';
  appEl.appendChild(frag);
}

function renderList(items) {
  const frag = document.createDocumentFragment();
  items.forEach(c => {
    const div = document.createElement('div');
    div.className = 'list-item';
    div.setAttribute('data-client-id', c.id);

    const name = document.createElement('div'); name.textContent = c.nome; name.style.fontWeight = '600';
    const meta = document.createElement('div'); meta.className = 'small'; meta.textContent = `${c.telefone} — ${c.endereco}`;

    div.append(name, meta);
    frag.appendChild(div);
  });
  appEl.innerHTML = '';
  appEl.appendChild(frag);
}

function renderTable(items) {
  const wrapper = document.createElement('div'); wrapper.className = 'table-wrapper';
  const table = document.createElement('table');
  const thead = document.createElement('thead');
  thead.innerHTML = '<tr><th>Nome</th><th>Telefone</th><th>Endereço</th></tr>';
  table.appendChild(thead);

  const tbody = document.createElement('tbody');
  items.forEach(c => {
    const tr = document.createElement('tr');
    tr.setAttribute('data-client-id', c.id);
    const tdName = document.createElement('td'); tdName.textContent = c.nome;
    const tdTel = document.createElement('td'); tdTel.textContent = c.telefone;
    const tdAddr = document.createElement('td'); tdAddr.textContent = c.endereco;
    tr.append(tdName, tdTel, tdAddr);
    tbody.appendChild(tr);
  });
  table.appendChild(tbody);
  wrapper.appendChild(table);
  appEl.innerHTML = '';
  appEl.appendChild(wrapper);
}

function showDetail(client) {
  detailContent.innerHTML = '';
  const title = document.createElement('h2'); title.textContent = client.nome;
  const tel = document.createElement('p'); tel.innerHTML = `<strong>Telefone:</strong> ${escapeText(client.telefone)}`;
  const addr = document.createElement('p'); addr.innerHTML = `<strong>Endereço:</strong> ${escapeText(client.endereco)}`;
  const rawPre = document.createElement('pre'); rawPre.textContent = JSON.stringify(client.raw, null, 2);
  rawPre.style.maxHeight = '200px'; rawPre.style.overflow = 'auto';

  detailContent.append(title, tel, addr, rawPre);
  detailEl.classList.remove('hidden');
}

function hideDetail() {
  detailEl.classList.add('hidden');
  detailContent.innerHTML = '';
}

function showStatus(text) {
  statusEl.textContent = text;
}

function escapeText(v) {
  if (v == null) return '';
  return String(v);
}