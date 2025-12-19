package Projeto;

import java.util.*;
import java.io.*;

public class ProjetoOnibus {
    public static void main(String[] args) {
        char opcao;
        Scanner sc = new Scanner(System.in);
        String raiz = "gerenciamentodeonibus/";
        String raizAlunos = raiz + "alunos/";
        String raizContratos = raiz + "contratos/";
        String raizPontos = raiz + "pontos/";

        do {
            opcao = menuPrincipal();
            switch (opcao) {
                case '1': // Alunos
                    gerenciarAlunos(raizAlunos);
                    break;
                case '2': // Contratos
                    gerenciarContratos(raizContratos, raizAlunos);
                    break;
                case '3': // Pontos
                    gerenciarPontos(raizPontos);
                    break;
                case '4': // Resetar
                    reset("", raiz);
                    reset("idAluno.txt", raizAlunos);
                    reset("idContrato.txt", raizContratos);
                    reset("idPonto.txt", raizPontos);
                    break;
                case '5': // Sair
                    System.out.println("Saindo...");
            }
        } while (opcao != '5');
    }

    private static char menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------\n"
                + "1 - Alunos\n"
                + "2 - Contratos\n"
                + "3 - Pontos\n"
                + "4 - Reset/inicializar\n"
                + "5 - Sair\n"
                + "---------------------------");
        System.out.print("Informe sua opção: ");
        return sc.next().charAt(0);
    }

    private static char menuSecundario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("---------------------------\n"
                + "1 - Cadastrar\n"
                + "2 - Buscar\n"
                + "3 - Listar\n"
                + "4 - Atualizar\n"
                + "5 - Remover\n"
                + "6 - Remover todos\n"
                + "7 - Voltar\n"
                + "---------------------------");
        System.out.print("Informe sua opção: ");
        return sc.next().charAt(0);
    }

    private static void gerenciarAlunos(String raizAlunos) {
        char opcao;
        Aluno a;
        int idAluno = leId("idAluno.txt");
        do {
            opcao = menuSecundario();
            switch (opcao) {
                case '1': // Cadastrar
                    a = preencheAluno(idAluno);
                    gravaAluno(raizAlunos, a);
                    idAluno++;
                    gravaId(idAluno, "idAluno.txt");
                    break;
                case '2': // Buscar
                    buscaAlunoPorId(raizAlunos);
                    break;
                case '3': // Listar
                    listarAlunos(raizAlunos);
                    break;
                case '4': // Atualizar
                    atualizarAluno(raizAlunos);
                    break;
                case '5': // Remover
                    removerAluno(raizAlunos);
                    break;
                case '6': // Remover todos
                    reset("idAluno.txt", raizAlunos);
                    break;
                case '7': // Sair
                    System.out.println("Voltando...");
            }
        } while (opcao != '7');
    }

    private static void gerenciarContratos(String raizContratos, String raizAlunos) {
        char opcao;
        Contrato c;
        int idContrato = leId("idContrato.txt");
        do {
            opcao = menuSecundario();
            switch (opcao) {
                case '1': // Cadastrar
                    c = preencheContrato(idContrato, raizContratos, raizAlunos);
                    gravaContrato(raizContratos, c);
                    idContrato++;
                    gravaId(idContrato, "idContrato.txt");
                    break;
                case '2': // Buscar
                    buscaContratoPorId(raizContratos);
                    break;
                case '3': // Listar
                    listarContratos(raizContratos);
                    break;
                case '4': // Atualizar
                    atualizarContrato(raizContratos);
                    break;
                case '5': // Remover
                    removerContrato(raizContratos);
                    break;
                case '6': // Remover todos
                    reset("idContrato.txt", raizContratos);
                    break;
                case '7': // Sair
                    System.out.println("Voltando...");
            }
        } while (opcao != '7');
    }

    private static void gerenciarPontos(String raizPontos) {
        char opcao;
        Ponto p;
        int idPonto = leId("idPonto.txt");
        do {
            opcao = menuSecundario();
            switch (opcao) {
                case '1': // Cadastrar
                    p = preenchePonto(idPonto);
                    gravaPonto(raizPontos, p);
                    idPonto++;
                    gravaId(idPonto, "idPonto.txt");
                    break;
                case '2': // Buscar
                    buscaPontoPorId(raizPontos);
                    break;
                case '3': // Listar
                    listarPontos(raizPontos);
                    break;
                case '4': // Atualizar
                    atualizarPonto(raizPontos);
                    break;
                case '5': // Remover
                    removerPonto(raizPontos);
                    break;
                case '6': // Remover todos
                    reset("idPonto.txt", raizPontos);
                    break;
                case '7': // Sair
                    System.out.println("Voltando...");
            }
        } while (opcao != '7');
    }

    private static Aluno preencheAluno(int id) {
        Scanner sc = new Scanner(System.in);
        Aluno a = new Aluno();
        a.id = id;
        System.out.println("Inserindo informações do aluno: ");
        System.out.print("Nome: ");
        a.nome = sc.nextLine();
        System.out.print("Telefone: ");
        a.telefone = sc.nextLine();
        System.out.print("Telefone do responsável: ");
        a.telefoneResponsavel = sc.nextLine();
        return a;
    }

    private static void gravaAluno(String raiz, Aluno a) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(raiz + a.id + ".txt", true));
            bw.append(a.id + "\n");
            bw.append(a.nome + "\n");
            bw.append(a.telefone + "\n");
            bw.append(a.telefoneResponsavel + "\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o aluno em arquivo");
            e.printStackTrace();
        }
    }

    private static void buscaAlunoPorId(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do aluno?: ");
        int id = sc.nextInt();
        Aluno a = leAluno(id, raiz);
        if (a != null)
            imprimeAluno(a);
        else
            System.out.println("Aluno não existe!");
    }

    private static void listarAlunos(String raiz) {
        File dir = new File(raiz);
        String[] arquivos = dir.list();
        for (String arquivo : arquivos) {
            int id = Integer.parseInt(arquivo.substring(0, arquivo.indexOf('.')));
            Aluno a = leAluno(id, raiz);
            System.out.println("----");
            imprimeAluno(a);
        }
    }

    private static Aluno leAluno(int id, String raiz) {
        Aluno a = new Aluno();
        try {
            BufferedReader br = new BufferedReader(new FileReader(raiz + id + ".txt"));
            a.id = Integer.parseInt(br.readLine());
            a.nome = br.readLine();
            a.telefone = br.readLine();
            a.telefoneResponsavel = br.readLine();
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Arquivo/aluno não encontrado");
            return null;
        }
        return a;
    }				

    private static void imprimeAluno(Aluno a) {
        if (a == null) return;
        System.out.println("ID: " + a.id + " - Nome: " + a.nome + ", Telefone: " + a.telefone + ", Telefone do responsável: " + a.telefoneResponsavel);
    }

    private static void atualizarAluno(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do aluno a ser atualizado?: ");
        int id = sc.nextInt();
        Aluno a = leAluno(id, raiz);
        if (a != null) {
            imprimeAluno(a);
            a = preencheAluno(id);
            File arquivo = new File(raiz + a.id + ".txt");
            arquivo.delete();
            gravaAluno(raiz, a);
        }
    }

    private static void removerAluno(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do aluno a ser removido?: ");
        int id = sc.nextInt();
        Aluno a = leAluno(id, raiz);
        if (a != null) {
            imprimeAluno(a);
            System.out.print("Tem certeza que deseja continuar? (s/n): ");
            char opcao = sc.next().charAt(0);
            if (opcao == 's') {
                File arquivo = new File(raiz + id + ".txt");
                if (arquivo.delete()) {
                    System.out.println("Aluno removido com sucesso!");
                }
            }
        } else {
            System.out.println("Aluno não existe!");
        }
    }

    private static Contrato preencheContrato(int id, String raizContratos, String raizAlunos) {
        Scanner sc = new Scanner(System.in);
        Contrato c = new Contrato();
        c.aluno = new Aluno();
        c.aluno = preencheAluno(leId("idAluno.txt")); // Aqui você pode implementar a busca do aluno
        System.out.print("Preço: ");
        c.preco = sc.nextDouble();
        c.ponto = new Ponto();
        c.ponto = preenchePonto(leId("idPonto.txt")); // Aqui você pode implementar a busca do ponto
        System.out.print("Início: ");
        c.inicio = sc.next();
        System.out.print("Fim: ");
        c.fim = sc.next();
        return c;
    }

    private static void gravaContrato(String raiz, Contrato c) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(raiz + c.aluno.id + ".txt", true));
            bw.append(c.aluno.id + "\n");
            bw.append(c.preco + "\n");
            bw.append(c.ponto.id + "\n");
            bw.append(c.inicio + "\n");
            bw.append(c.fim + "\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o contrato em arquivo");
            e.printStackTrace();
        }
    }

    private static void buscaContratoPorId(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do contrato?: ");
        int id = sc.nextInt();
        Contrato c = leContrato(id, raiz);
        if (c != null)
            imprimeContrato(c);
        else
            System.out.println("Contrato não existe!");
    }

    private static void listarContratos(String raiz) {
        File dir = new File(raiz);
        String[] arquivos = dir.list();
        for (String arquivo : arquivos) {
            int id = Integer.parseInt(arquivo.substring(0, arquivo.indexOf('.')));
            Contrato c = leContrato(id, raiz);
            System.out.println("----");
            imprimeContrato(c);
        }
    }

    private static Contrato leContrato(int id, String raiz) {
        Contrato c = new Contrato();
        try {
            BufferedReader br = new BufferedReader(new FileReader(raiz + id + ".txt"));
            c.aluno = leAluno(Integer.parseInt(br.readLine()), raiz);
            c.preco = Double.parseDouble(br.readLine());
            c.ponto = new Ponto();
            c.ponto.id = Integer.parseInt(br.readLine());
            c.inicio = br.readLine();
            c.fim = br.readLine();
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Arquivo/contrato não encontrado");
            return null;
        }
        return c;
    }

    private static void imprimeContrato(Contrato c) {
        if (c == null) return;
        System.out.println("ID Aluno: " + c.aluno.id + " - Preço: " + c.preco + ", Ponto: " + c.ponto.id + ", Início: " + c.inicio + ", Fim: " + c.fim);
    }

    private static void atualizarContrato(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do contrato a ser atualizado?: ");
        int id = sc.nextInt();
        Contrato c = leContrato(id, raiz);
        if (c != null) {
            imprimeContrato(c);
            c = preencheContrato(id, raiz, raiz);
            File arquivo = new File(raiz + c.aluno.id + ".txt");
            arquivo.delete();
            gravaContrato(raiz, c);
        }
    }

    private static void removerContrato(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do contrato a ser removido?: ");
        int id = sc.nextInt();
        Contrato c = leContrato(id, raiz);
        if (c != null) {
            imprimeContrato(c);
            System.out.print("Tem certeza que deseja continuar? (s/n): ");
            char opcao = sc.next().charAt(0);
            if (opcao == 's') {
                File arquivo = new File(raiz + id + ".txt");
                if (arquivo.delete()) {
                    System.out.println("Contrato removido com sucesso!");
                }
            }
        } else {
            System.out.println("Contrato não existe!");
        }
    }

    private static Ponto preenchePonto(int id) {
        Scanner sc = new Scanner(System.in);
        Ponto p = new Ponto();
        p.id = id;
        System.out.println("Inserindo informações do ponto: ");
        System.out.print("Nome: ");
        p.nome = sc.nextLine();
        System.out.print("Endereço: ");
        p.endereco = sc.nextLine();
        return p;
    }

    private static void gravaPonto(String raiz, Ponto p) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(raiz + p.id + ".txt", true));
            bw.append(p.id + "\n");
            bw.append(p.nome + "\n");
            bw.append(p.endereco + "\n");
            bw.flush();
            bw.close();
        } catch (IOException e) {
            System.out.println("Não foi possível gravar o ponto em arquivo");
            e.printStackTrace();
        }
    }

    private static void buscaPontoPorId(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do ponto?: ");
        int id = sc.nextInt();
        Ponto p = lePonto(id, raiz);
        if (p != null)
            imprimePonto(p);
        else
            System.out.println("Ponto não existe!");
    }

    private static void listarPontos(String raiz) {
        File dir = new File(raiz);
        String[] arquivos = dir.list();
        for (String arquivo : arquivos) {
            int id = Integer.parseInt(arquivo.substring(0, arquivo.indexOf('.')));
            Ponto p = lePonto(id, raiz);
            System.out.println("----");
            imprimePonto(p);
        }
    }

    private static Ponto lePonto(int id, String raiz) {
        Ponto p = new Ponto();
        try {
            BufferedReader br = new BufferedReader(new FileReader(raiz + id + ".txt"));
            p.id = Integer.parseInt(br.readLine());
            p.nome = br.readLine();
            p.endereco = br.readLine();
            br.close();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Arquivo/ponto não encontrado");
            return null;
        }
        return p;
    }

    private static void imprimePonto(Ponto p) {
        if (p == null) return;
        System.out.println("ID: " + p.id + " - Nome: " + p.nome + ", Endereço: " + p.endereco);
    }

    private static void atualizarPonto(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do ponto a ser atualizado?: ");
        int id = sc.nextInt();
        Ponto p = lePonto(id, raiz);
        if (p != null) {
            imprimePonto(p);
            p = preenchePonto(id);
            File arquivo = new File(raiz + p.id + ".txt");
            arquivo.delete();
            gravaPonto(raiz, p);
        }
    }

    private static void removerPonto(String raiz) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Qual o id do ponto a ser removido?: ");
        int id = sc.nextInt();
        Ponto p = lePonto(id, raiz);
        if (p != null) {
            imprimePonto(p);
            System.out.print("Tem certeza que deseja continuar? (s/n): ");
            char opcao = sc.next().charAt(0);
            if (opcao == 's') {
                File arquivo = new File(raiz + id + ".txt");
                if (arquivo.delete()) {
                    System.out.println("Ponto removido com sucesso!");
                }
            }
        } else {
            System.out.println("Ponto não existe!");
        }
    }

    private static void reset(String arquivoId, String raiz) {
        if (!arquivoId.isBlank())
            gravaId(0, arquivoId);
        File dir = new File(raiz);
        if (!dir.exists()) {
            dir.mkdir(); // cria o diretório raiz
        } else { // apaga tudo se o diretório já existir
            String[] arquivos = dir.list();
            for (String arq : arquivos) {
                File ar = new File(raiz + arq);
                if (ar.delete())
                    System.out.println(arq + " apagado com sucesso");
            }
        }
    }

    private static int leId(String arquivo) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivo));
            int id = Integer.parseInt(br.readLine());
            br.close();
            return id;
        } catch (FileNotFoundException e) {
            System.out.println("Inicializando o sistema...");
            gravaId(0, arquivo);
            return 0;
        } catch (NumberFormatException e) {
            System.out.println("O arquivo está corrompido");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Não foi possível acessar o arquivo");
            e.printStackTrace();
        }
        return 0;
    }

    private static void gravaId(int id, String arquivo) {
        try {
            PrintWriter pw = new PrintWriter(arquivo);
            pw.println(id); // grava id no arquivo
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("Não foi possível gravar o id");
            e.printStackTrace();
        }
    }
}