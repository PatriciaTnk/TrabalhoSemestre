/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import franquiamedica.FinanceiroAdm;
import franquiamedica.Franquia;
import franquiamedica.Utilitario;
import static franquiamedica.Utilitario.diaSistema;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Scanner;

public class ControllerResponsavelFranquia {

    private BigDecimal montanteCons = new BigDecimal("0");

    private BigDecimal montanteProc = new BigDecimal("0");

    public BigDecimal getMontanteCons() {
        return montanteCons;
    }

    public BigDecimal getMontanteProc() {
        return montanteProc;
    }

    public void setMontanteCons(BigDecimal montanteCons) {
        this.montanteCons = montanteCons.setScale(2, RoundingMode.HALF_DOWN);
    }

    public void setMontanteProc(BigDecimal montanteProc) {
        this.montanteProc = montanteProc.setScale(2, RoundingMode.HALF_DOWN);
    }

    public boolean alterarTipodoUsuario(Controller controller, long idPessoa) {
        for (int i = 0; i < controller.getP().pessoas.length; ++i) {
            if (controller.getP().pessoas[i].getId() == idPessoa) {
                controller.getP().pessoas[i].setTipoUsuario("Medico");
                return true;
            }
        }
        return false;
    }

    public void relatorioFinanceiroMedico(Controller controller) {
        controller.getFinMed().mostraTodos();
    }

    public void relatorioFinanceiroFranquia(Controller controller) {
        controller.getFin().mostraTodos();
    }

    public void pagamentoMensalParaMatriz(Controller controller, Franquia franquia) {
        int[] mesesUm = {1, 3, 5, 7, 8, 10, 12};
        int[] mesesZero = {4, 6, 9, 11};
        int qtdMeses = Utilitario.diaSistema.getMonthValue();

        LocalDateTime dataParaIncrementar = Utilitario.diaSistema;

        BigDecimal montanteCons = new BigDecimal("0");
        montanteCons = montanteCons.setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal montanteConsM = new BigDecimal("0");
        montanteCons = montanteCons.setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal montanteProc = new BigDecimal("0");
        montanteProc = montanteProc.setScale(2, RoundingMode.HALF_DOWN);

        BigDecimal montanteProcM = new BigDecimal("0");
        montanteProc = montanteProc.setScale(2, RoundingMode.HALF_DOWN);

        for (int i = 0; i < qtdMeses; ++i) {
            if ((qtdMeses + 12) % 12 == 2 && !Year.isLeap(diaSistema.getYear())) {
                dataParaIncrementar = dataParaIncrementar.minusDays(28);
            } else if ((qtdMeses + 12) % 12 == 2 && Year.isLeap(diaSistema.getYear())) {
                dataParaIncrementar = dataParaIncrementar.minusDays(29);
            } else {
                if (mesesZero.equals(qtdMeses)) {
                    for (int j = 0; j < mesesZero.length; ++j) {
                        if ((qtdMeses + 12) % 12 == mesesZero[j]) {
                            dataParaIncrementar = dataParaIncrementar.minusDays(30);
                        }
                    }
                } else {
                    for (int l = 0; l < mesesUm.length; ++l) {
                        if ((qtdMeses + 12) % 12 == mesesUm[l]) {
                            dataParaIncrementar = dataParaIncrementar.minusDays(31);
                        }
                    }
                }
            }
        }

        for (int j = 0; j < controller.getC().consultas.length; ++j) {
            if (controller.getC().consultas[j].getDiaHorario().isBefore(LocalDateTime.of(2023, qtdMeses, 1, 12, 00))
                    && controller.getC().consultas[j].getDiaHorario().isAfter(dataParaIncrementar)) {
                montanteCons = montanteCons.add(controller.getC().consultas[j].getValor());
                montanteConsM = montanteConsM.add(controller.getC().consultas[j].getValor());
                FinanceiroAdm novoSaida = new FinanceiroAdm(franquia);

                novoSaida.setTipoDeMovim("Saida");
                novoSaida.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
                novoSaida.setValor(montanteCons);
                controller.getFin().adiciona(novoSaida);

                FinanceiroAdm novoSaidaM = new FinanceiroAdm(franquia);

                novoSaida.setTipoDeMovim("Saida");
                novoSaida.setDescritivo("Valor: " + montanteProc + "\nPara Medicos");
                novoSaida.setValor(montanteConsM);
                controller.getFin().adiciona(novoSaidaM);
            }
        }

        //calculo valor para a franquia
        MathContext res = new MathContext(100);

        MathContext n = new MathContext(30);
        montanteCons.divide(montanteCons.multiply(montanteCons, n), res);

        FinanceiroAdm novoSaida = new FinanceiroAdm(franquia);

        novoSaida.setTipoDeMovim("Saida");
        novoSaida.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
        novoSaida.setValor(montanteCons);
        controller.getFin().adiciona(novoSaida);

        //calculo valor para o medico
        MathContext resM = new MathContext(100);

        MathContext nM = new MathContext(70);
        montanteConsM.divide(montanteConsM.multiply(montanteConsM, nM), resM);

        FinanceiroAdm novoSaidaM = new FinanceiroAdm(franquia);

        novoSaida.setTipoDeMovim("Saida");
        novoSaida.setDescritivo("Valor: " + montanteProc + "\nPara Medicos");
        novoSaida.setValor(montanteConsM);
        controller.getFin().adiciona(novoSaidaM);

        for (int j = 0; j < controller.getProc().proceds.length; ++j) {
            if (controller.getProc().proceds[j].getDiaHorario().isBefore(LocalDateTime.of(2023, qtdMeses, 1, 12, 00))
                    && controller.getProc().proceds[j].getDiaHorario().isAfter(dataParaIncrementar)) {
                montanteProc = montanteProc.add(controller.getProc().proceds[j].getValorPro());
                montanteProcM = montanteProcM.add(controller.getProc().proceds[j].getValorPro());

                FinanceiroAdm novoSaidaProc = new FinanceiroAdm(franquia);
                novoSaidaProc.setTipoDeMovim("Saida");
                novoSaidaProc.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
                novoSaidaProc.setValor(montanteProc);
                controller.getFin().adiciona(novoSaidaProc);

                FinanceiroAdm novoSaidaProcM = new FinanceiroAdm(franquia);
                novoSaidaProc.setTipoDeMovim("Saida");
                novoSaidaProc.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
                novoSaidaProc.setValor(montanteProcM);
                controller.getFin().adiciona(novoSaidaProcM);
            }
        }

        //valor para a franquia e para o medico é o mesmo, mas vou deixar o calculo separado
        MathContext m = new MathContext(50);
        montanteProc.divide(montanteProc.multiply(montanteProc, m), res);

        FinanceiroAdm novoSaidaProc = new FinanceiroAdm(franquia);
        novoSaidaProc.setTipoDeMovim("Saida");
        novoSaidaProc.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
        novoSaidaProc.setValor(montanteProc);
        controller.getFin().adiciona(novoSaidaProc);

        //calculo valor de procedimento para o medico, caso fosse != de 50%
        MathContext mM = new MathContext(50);
        montanteProcM.divide(montanteProcM.multiply(montanteProcM, mM), resM);

        FinanceiroAdm novoSaidaProcM = new FinanceiroAdm(franquia);
        novoSaidaProc.setTipoDeMovim("Saida");
        novoSaidaProc.setDescritivo("Valor: " + montanteProc + "\nPara Franquia: " + franquia);
        novoSaidaProc.setValor(montanteProcM);
        controller.getFin().adiciona(novoSaidaProcM);

    }

}
