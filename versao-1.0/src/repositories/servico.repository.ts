import servico from "../entities/servico.js"

export async function create(data: {
  tipo: string
  data: Date
  prazo: number
  garantia: number
  clienteId: number
}) {
  return servico.create({ data })
}

export async function findAll() {
  return servico.findMany({
    include: { cliente: true }
  })
}

export async function findById(id: number) {
  return servico.findUnique({
    where: { id },
    include: { cliente: true }
  })
}

export async function update(id: number, data: any) {
  return servico.update({
    where: { id },
    data
  })
}

export async function remove(id: number) {
  return servico.delete({
    where: { id }
  })
}