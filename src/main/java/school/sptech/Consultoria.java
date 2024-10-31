package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores = new ArrayList<>();

    public Consultoria() {
    }

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() < vagas) {
            desenvolvedores.add(desenvolvedor);
            return true;
        }

        return false;
    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack() == true) {
            desenvolvedores.add(desenvolvedor);
            return true;
        }

        return false;
    }

    public Double getTotalSalarios() {
        Double salarioTotalConsultoria = 0.0;
        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            salarioTotalConsultoria += desenvolvedor.calcularSalario();
        }

        return salarioTotalConsultoria;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer qtdDevsMobile = 0;

        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor devAtual = desenvolvedores.get(i);
            if (devAtual instanceof DesenvolvedorMobile) {
                qtdDevsMobile++;
            }
        }

        return qtdDevsMobile;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> buscarMaiorSalario = new ArrayList<>();

        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor.calcularSalario() >= salario) {
                buscarMaiorSalario.add(desenvolvedor);
            }
        }

        return buscarMaiorSalario;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }

        Desenvolvedor menorSalario = desenvolvedores.get(0);
        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor desenvolvedorAtual = desenvolvedores.get(i);
            if (desenvolvedorAtual.calcularSalario() < menorSalario.calcularSalario()) {
                menorSalario = desenvolvedorAtual;
            }
        }

        return menorSalario;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devPorTecnologia = new ArrayList<>();

        for (int i = 0; i < desenvolvedores.size(); i++) {
            Desenvolvedor desenvolvedorAtual = desenvolvedores.get(i);
            if (desenvolvedorAtual instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) desenvolvedorAtual).getLinguagem().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorMobile) desenvolvedorAtual).getPlataforma().equalsIgnoreCase(tecnologia)) {
                    devPorTecnologia.add(desenvolvedorAtual);
                }
            }

            if (desenvolvedorAtual instanceof DesenvolvedorWeb) {
                if (((DesenvolvedorWeb) desenvolvedorAtual).getBackend().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorWeb) desenvolvedorAtual).getFrontend().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorWeb) desenvolvedorAtual).getSgbd().equalsIgnoreCase(tecnologia)) {
                    devPorTecnologia.add(desenvolvedorAtual);
                }
            }
        }

        return devPorTecnologia;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        Double salarioTotal = 0.0;

        for (Desenvolvedor desenvolvedor : desenvolvedores) {
            if (desenvolvedor instanceof DesenvolvedorMobile) {
                DesenvolvedorMobile devMobile = (DesenvolvedorMobile) desenvolvedor;

                if (devMobile.getPlataforma().equalsIgnoreCase(tecnologia) ||
                        devMobile.getLinguagem().equalsIgnoreCase(tecnologia)) {
                    salarioTotal += devMobile.calcularSalario();
                }
            }

            if (desenvolvedor instanceof DesenvolvedorWeb) {
                DesenvolvedorWeb devWeb = (DesenvolvedorWeb) desenvolvedor;

                if (devWeb.getSgbd().equalsIgnoreCase(tecnologia) ||
                        devWeb.getBackend().equalsIgnoreCase(tecnologia) ||
                        devWeb.getFrontend().equalsIgnoreCase(tecnologia)) {
                    salarioTotal += devWeb.calcularSalario();
                }
            }
        }

        return salarioTotal;
    }
}