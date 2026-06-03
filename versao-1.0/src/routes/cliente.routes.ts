//Kenzo
import { Router } from "express"
import * as clienteController from "../controllers/cliente.controllers.js"
import { validateDto } from "../middlewares/validate.dto.js"

const router = Router()
router.post("/client", clienteController.createCliente)
router.get("/client", clienteController.getAllClientes)
router.get("/client/:id", clienteController.getClienteById)
router.put("/client/:id", clienteController.updateUser)
router.delete("/client/:id", clienteController.deleteUser)

export default router

