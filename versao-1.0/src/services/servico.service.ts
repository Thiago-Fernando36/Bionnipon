import * as servicoRepository from "../repositories/servico.repository.js"

export async function createServico(data: {
  tipo: string;
  data: string | Date;
  prazo: number;
  garantia: number;
  clienteId: number;
}) {
  const dataConvertida = new Date(data.data)

  if (isNaN(dataConvertida.getTime())) {
    throw new Error("Data inválida. Use YYYY-MM-DD ou uma Data válida.")
  }

  const proximaVisita = new Date(dataConvertida)
  proximaVisita.setDate(proximaVisita.getDate() + data.prazo)

  const fimGarantia = new Date(dataConvertida)
  fimGarantia.setDate(fimGarantia.getDate() + data.garantia)

  const servicoCriado = await servicoRepository.create({
    tipo: data.tipo,
    data: dataConvertida,
    prazo: data.prazo,
    garantia: data.garantia,
    clienteId: data.clienteId
  })

  return {
    ...servicoCriado,
    proximaVisita,
    fimGarantia
  }
}

export async function getAllServicos() {
  return await servicoRepository.findAll()
}

export async function getServicoById(id: number) {
  return await servicoRepository.findById(id)
}

export async function updateServico(id: number, data: {
  tipo?: string;
  data?: string | Date;
  prazo?: number;
  garantia?: number;
}) {
  const servico = await servicoRepository.findById(id)
  if (!servico) return null

  let dataConvertida = servico.data

  if (data.data) {
    dataConvertida = new Date(data.data)
    if (isNaN(dataConvertida.getTime())) {
      throw new Error("Data inválida na atualização.")
    }
  }

  return await servicoRepository.update(id, {
    ...data,
    data: dataConvertida,
  })
}

export async function deleteServico(id: number) {
  const servico = await servicoRepository.findById(id)
  if (!servico) return null

  return await servicoRepository.remove(id)
}