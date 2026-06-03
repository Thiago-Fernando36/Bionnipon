import { Router } from "express"
import * as estoqueController from "../controllers/estoque.controllers.js"
import { validateDto } from "../middlewares/validate.dto.js"

const router = Router()
router.post("/estoque", estoqueController.createEstoque)
router.get("/estoque", estoqueController.getAllEstoque)
router.get("/estoque/:id", estoqueController.getEstoqueById)
router.put("/estoque/:id", estoqueController.updateEstoque)
router.delete("/estoque/:id", estoqueController.deleteEstoque)

export default router

