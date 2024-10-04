package s5_dokotera.controller;

import s5_dokotera.model.Simplex;

public class MedicamentController {
    public static void main(String[] args) {
        Simplex solver = new Simplex();
        solver.setZ(new double[] { -1, -1, 0, 0, 0, 0, 0, 0, 1, 0 });
        solver.setInequation(new double[][] { new double[] { 2, 1, -1, 0, 0, 1, 0, 0, 0, 12 }, new double[] {5, 8, 0, -1, 0, 0, 1, 0, 0, 74 }, new double[] {1, 6, 0, 0, -1, 0, 0, 1, 0, 24 }});
        solver.setSommeArtificielle(new double[] { -8, -15, 1, 1, 1, 0, 0, 0, 0, -110 });
        
        double[][] matrice1 = solver.matriceDeuxPhase();
        double[][] matrice2 = solver.algoSimplexedeuxphases(matrice1);
        double[][] matrice3 = solver.withoutArtificial(matrice2);
        double[][] matrice4 = solver.algoSimplexeMin(matrice3);
        System.out.println("Minimiser + somme de variable artificielle");
        for (int i = 0; i < matrice2.length; i++)
        {
            System.out.println(solver.baseDeuxPhases[i] + "  ");
            for (int j = 0; j < matrice2[i].length; j++)
            {
                System.out.println(matrice2[i][j] + "  ");
            }
            System.out.println(" ");
        }
        for (int i = 0; i < matrice3.length; i++)
        {
            System.out.println(solver.bazy[i] + "  ");
            for (int j = 0; j < matrice3[i].length; j++)
            {
                System.out.println(matrice3[i][j] + "  ");
            }
            System.out.println(" ");
        }
        for (int i = 0; i < matrice4.length; i++)
        {
            System.out.println(solver.bazy[i] + "  ");
            for (int j = 0; j < matrice4[i].length; j++)
            {
                System.out.println(matrice4[i][j] + "  ");
            }
            System.out.println(" ");
        }
        System.out.println("Manala variable artificielle");
    }
}
