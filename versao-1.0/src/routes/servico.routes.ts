//Kenzo
import { Router } from "express"
import * as servicoController from "../controllers/servico.controllers.js"
import { validateDto } from "../middlewares/validate.dto.js"

const router = Router()
router.post("/servico", servicoController.createServico)
router.get("/servico", servicoController.getAllServicos)
router.get("/servico/:id", servicoController.getServicoById)
router.put("/servico/:id", servicoController.updateServico)
router.delete("/servico/:id", servicoController.deleteServico)


export default router

