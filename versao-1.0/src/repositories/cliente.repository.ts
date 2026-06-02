import Cliente from "../entities/cliente.js"

export async function create(data: {
  nome: string
  telefone: string
  endereco: string
}) {
  return Cliente.create({ data })
}

export async function findAll() {
  return Cliente.findMany({
    include: { servicos: true }
  })
}

export async function findById(id: number) {
  return Cliente.findUnique({
    where: { id },
    include: { servicos: true }
  })
}

export async function update(id: number, data: any) {
  return Cliente.update({
    where: { id },
    data
  })
}

export async function remove(id: number) {
  return Cliente.delete({
    where: { id }
  })
}