package s5_dokotera.model;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    static String[] base = {"2","0","1","Z"};

    // base ilaina @ le 2 phase
    static String[] baseDeuxPhases = {"5", "6", "7", "Z", "A"};
    public static void main(String[] args) {
        int[][] A = {{1,1,2},{2,-1,2},{4,1,4}};
        int[] B = {-1,-4,-2};

/***Partie Maximisation***/
        /*String[][] Cont = {{"1","2"},{"2","1"},{"0","1"}};
        String[] zoneInegalite = {"700","800","300"};
        String[] Z = {"4","5"};

        for (int i = 0; i < simplextomatrice(Cont, Z, zoneInegalite).length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < simplextomatrice(Cont, Z, zoneInegalite)[i].length; j++) {
                System.out.print(simplextomatrice(Cont, Z, zoneInegalite)[i][j]+" ");
            }
            System.out.println("");
        }
        for (int i = 0; i < algoSimplexe(simplextomatrice(Cont, Z, zoneInegalite)).length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < algoSimplexe(simplextomatrice(Cont, Z, zoneInegalite))[i].length; j++) {
                System.out.print(algoSimplexe(simplextomatrice(Cont, Z, zoneInegalite))[i][j]+"\t\t");
            }
            System.out.println("");
        }
/***Partie deux phases**/
        System.out.println("Deux phases");
        String[][] deuxPhases = {{"2","1"},{"5","8"},{"1","6"}};
        String[] Max = {"1","1"};
        String[][] formematricielle = {{ "-1", "0", "0", "1", "0", "0", "0", "12" }, {"0", "-1", "0", "0", "1", "0", "0", "74" }, {"0", "0", "-1", "0", "0", "1", "0", "24" } };
        String[] sommeArtificielle = {"-8","-15","1","1","1","0","0","0","0","-110"};
        String[] baseFormax = {"-1","-1","0","0","0","0","0","0","1","0"};
        for (int i = 0; i < matricedeuxphases(deuxPhases, sommeArtificielle, formematricielle, baseFormax).length; i++) {
            System.out.print(baseDeuxPhases[i]+"  ");
            for (int j = 0; j < matricedeuxphases(deuxPhases, sommeArtificielle, formematricielle, baseFormax)[i].length; j++) {
                System.out.print(matricedeuxphases(deuxPhases, sommeArtificielle, formematricielle, baseFormax)[i][j]+" ");
            }
            System.out.println("");
        }
        
        
        String[][] artificielle = algoSimplexedeuxphases(matricedeuxphases(deuxPhases, sommeArtificielle, formematricielle, baseFormax));
        System.out.println("Minimiser somme des variables artificieles");
        for (int i = 0; i < artificielle.length; i++) {
            System.out.print(baseDeuxPhases[i]+"  ");
            for (int j = 0; j < artificielle[i].length; j++) {
                System.out.print(artificielle[i][j]+"\t\t");
            }
            System.out.println("");
        }
        String[][] newmat = withoutArtificial(artificielle);
        System.out.println("Minimisation");
        for (int i = 0; i < newmat.length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < newmat[i].length; j++) {
                System.out.print(newmat[i][j]+"\t\t");
            }
            System.out.println("");
        }
        String[][] maximiser = algoSimplexeMin(newmat);
        System.out.println("Resultat Final");
        for (int i = 0; i < maximiser.length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < maximiser[i].length; j++) {
                System.out.print(maximiser[i][j]+"\t\t");
            }
            
            System.out.println("");
        }
/***Partie Minimisation**/
        /*String[][] Cont = {{"-3","2"},{"-1","2"},{"1","1"}};
        String[] zoneInegalite = {"2","4","5"};
        String[] Z = {"-1","-2"};
        System.out.println("Partie Minimisation");
        for (int i = 0; i < simplextomatrice(Cont, Z, zoneInegalite).length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < simplextomatrice(Cont, Z, zoneInegalite)[i].length; j++) {
                System.out.print(simplextomatrice(Cont, Z, zoneInegalite)[i][j]+" ");
            }
            System.out.println("");
        }
        String[][] formin = algoSimplexeMin(simplextomatrice(Cont, Z, zoneInegalite));
        for (int i = 0; i < formin.length; i++) {
            System.out.print(base[i]+"  ");
            for (int j = 0; j < formin[i].length; j++) {
                System.out.print(formin[i][j]+"\t\t");
            }
            System.out.println("");
        }
        /*int column = indexMaxZ(simplextomatrice(Cont, Z, zoneInegalite));
        int line = getPivot(simplextomatrice(Cont, Z, zoneInegalite),column);
        System.out.println("Column:"+column);
        System.out.println("Line:"+line);
        System.out.println(changePosition("3/4"));*/
        //gaussjordan(A,B);
        //triangleSup(A,B);
    }
/***deux phases**/
    static String[][] matricedeuxphases(String[][] Systeme,String[] A,String[][] fmatrice,String[] basemax){
        String[][] matrice = Systeme;
        matrice = Arrays.copyOf(matrice,matrice.length+1);
        int a = 0, b = 0, r = 0;
        for (int i = 0 ; i < matrice.length-1; i++) {
            int j = matrice[i].length;
            matrice[i] = Arrays.copyOf(matrice[i],matrice[i].length+fmatrice[0].length);
                while (j < matrice[i].length) {
                    matrice[i][j] = fmatrice[a][b];
                    b++;
                    j++;
                }
                b = 0;
                a++;
        }
        matrice = Arrays.copyOf(matrice,matrice.length+1);
        matrice[matrice.length-2] = basemax;
        matrice[matrice.length-1] = A;
        return matrice;
    }

    static double changeIffraction(String chf){
        double value = 0;
        if(chf.contains("/")){
            if(chf.contains("-")){
                String numerateur = String.valueOf(chf.charAt(1));
                String denominateur = String.valueOf(chf.charAt(3));
                value = -Double.parseDouble(numerateur) / Double.parseDouble(denominateur);
            } else {
                String numerateur = String.valueOf(chf.charAt(0));
                String denominateur = String.valueOf(chf.charAt(2));
                value = Double.parseDouble(numerateur) / Double.parseDouble(denominateur);
            }
        }else{
            value = Double.valueOf(chf);
        }
        return value;
    }

    static int indexMinA(String[][] matrix){
        int lastIndex = matrix.length-1;
        int index = 0;
        for (int i = 0; i < matrix[lastIndex].length; i++) {
            if(i<matrix[lastIndex].length-1 && changeIffraction(matrix[lastIndex][i]) < changeIffraction(matrix[lastIndex][index])) index = i;
        }
        return index;
    }

    static int getPivotdeuxPhases(String[][] matrix, int indexMinA){
        int lastIndex = matrix[0].length-1;
        int indexZ = matrix.length-2;
        int index = 0;
        double valeur = 0;
        for (int i = 0; i < matrix.length-1; i++) {
            if(changeIffraction(matrix[i][indexMinA]) > 0 && i!= indexZ){
                if(i == 0 || valeur == 0){
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinA]);
                    index = i;
                } else if (i > 0 && changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinA]) < valeur) {
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinA]);
                    index = i;
                }
            }
        }
        return index;
    }

    static boolean checkIfSommeartnull(String[][] matrix) throws Iresoluble{
        int lastIndex = matrix.length-1;
        int lastIndexline = matrix[lastIndex].length-1;
        for (int i = 0; i < matrix[lastIndex].length-1; i++) {
            if(changeIffraction(matrix[lastIndex][i]) < 0 && changeIffraction(matrix[lastIndex][lastIndexline]) == 0) throw new Iresoluble();
            else if(changeIffraction(matrix[lastIndex][i]) < 0) return false;
        }
        return true;
    }

    static String[][] algoSimplexedeuxphases(String[][] matrix){
        int column = indexMinA(matrix);
        int line = getPivotdeuxPhases(matrix,column);
        baseDeuxPhases[line] = String.valueOf(column);
        double pivot = changeIffraction(matrix[line][column]);
        boolean test = false;
        if(checkFraction(matrix[line][column])){
            pivot = changeIffraction(changePosition(matrix[line][column]));
            test = true;
        }
        for (int i = 0; i < matrix[line].length; i++) {
            double value = 0;
            if(test){
                value = pivot*changeIffraction(matrix[line][i]);
            }else{
                value = changeIffraction(matrix[line][i])/pivot;
            }
            matrix[line][i] = String.valueOf(value);
        }
        for (int i = 0; i < matrix.length; i++) {
            if(i!=line){
                double fraction = changeIffraction(matrix[i][column])/changeIffraction(matrix[line][column]);
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = changeIffraction(matrix[i][j]);
                    double lignePivot = changeIffraction(matrix[line][j]);
                    temp = temp - fraction*lignePivot;
                    matrix[i][j] = String.valueOf(temp);
                }
            }
        }
        try {
            if(!checkIfSommeartnull(matrix)){
                matrix = algoSimplexedeuxphases(matrix);
            }
        } catch (Iresoluble ex) {
            System.out.println(ex.getMessage());
        }
        return matrix;
    }

    static int[] getArtificial(String[][] matrix){
        int lastIndex = matrix.length-1;
        int indexZ = matrix[0].length-2;
        int[] indice = new int[0];
        int index = 0;
        for (int i = 0; i < matrix[lastIndex].length-1; i++) {
            if(changeIffraction(matrix[lastIndex][i]) == 1 || i == indexZ ){
                indice = Arrays.copyOf(indice,indice.length+1);
                indice[index] = i;
                index++;
            }
        }
        return indice;
    }

    static String[][] withoutArtificial(String[][] matrix){
        int[] skipColummns = getArtificial(matrix);
        int skipRow = matrix.length-1;
        String[][] newArray = new String[matrix.length-1][matrix[0].length - skipColummns.length];

        for (int i = 0; i < matrix.length; i++) {
            if (i == skipRow) {
                continue;
            }
            int newColumn = 0;
            for (int j = 0; j < matrix[i].length; j++) {
                if (contenir(skipColummns, j)){
                    continue;
                }
                newArray[i < skipRow ? i : i-1][newColumn++] = matrix[i][j];
            }
        }
        int lastRow = newArray.length - 1;
        for (int i = 0; i < newArray[lastRow].length; i++) {
            double change = 0;
            if(changeIffraction(newArray[lastRow][i]) != 0)change = changeIffraction(newArray[lastRow][i])*-1;
            newArray[lastRow][i] = String.valueOf(change);
        }
        return newArray;
    }

    static boolean contenir(int[] tableau, int valeur){
        for (int i : tableau) {
            if (i == valeur){
                return true;
            }
        }
        return false;
    }

/***Minimisation**/
    static boolean checkIfMinimiser(String[][] matrix){
        int lastIndex = matrix.length-1;
        for (int i = 0; i < matrix[lastIndex].length-1; i++) {
            if(changeIffraction(matrix[lastIndex][i]) < 0) return false;
        }
        return true;
    }

    static int getPivotMin(String[][] matrix, int indexMinZ){
        int lastIndex = matrix[0].length-1;
        int index = 0;
        double valeur = 0;
        for (int i = 0; i < matrix.length-1; i++) {
            if(changeIffraction(matrix[i][indexMinZ]) > 0){
                if(i == 0 || valeur == 0){
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinZ]);
                    index = i;
                } else if (i > 0 && changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinZ]) < valeur) {
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMinZ]);
                    index = i;
                }
            }
        }
        return index;
    }

    static String[][] algoSimplexeMin(String[][] matrix){
        int column = indexMinA(matrix);
        int line = getPivotMin(matrix,column);
        base[line] = String.valueOf(column);
        double pivot = changeIffraction(matrix[line][column]);
        boolean test = false;
        if(checkFraction(matrix[line][column])){
            pivot = changeIffraction(changePosition(matrix[line][column]));
            test = true;
        }
        for (int i = 0; i < matrix[line].length; i++) {
            double value = 0;
            if(test){
                value = pivot*changeIffraction(matrix[line][i]);
            }else{
                value = changeIffraction(matrix[line][i])/pivot;
            }
            matrix[line][i] = String.valueOf(value);
        }
        for (int i = 0; i < matrix.length; i++) {
            if(i!=line){
                double fraction = changeIffraction(matrix[i][column])/changeIffraction(matrix[line][column]);
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = changeIffraction(matrix[i][j]);
                    double lignePivot = changeIffraction(matrix[line][j]);
                    temp = temp - fraction*lignePivot;
                    matrix[i][j] = String.valueOf(temp);
                }
            }
        }
        
        try {
            if(!checkIfMinimiser(matrix))matrix = algoSimplexeMin(matrix);
        } catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return matrix;
    }
/***Gauss et gauusjordan**/
    static void triangleSup(int[][] A, int[] B) {
        for (int k = 0; k < A.length-1; k++) {
            for (int i = k+1; i < A.length; i++) {
                double fraction = A[i][k]/A[k][k];
                for (int j = 0; j < A[i].length; j++) {
                    A[i][j] = (int) (A[i][j]-fraction*A[k][j]);
                }
                B[i] = (int) (B[i]-fraction*B[k]);
            }
        }
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                System.out.print(A[i][j]+" ");
            }
            System.out.println(B[i]);
        }
    }

    static void gaussjordan(int[][] A, int[] B) {
        int n = A.length;
        int m = A[0].length;

        int[][] newMat = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                newMat[i][j] = A[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            int pivRow = i;
            while (pivRow < n && newMat[pivRow][i] == 0) {
                pivRow++;
            }

            if (pivRow == n) {
                continue;
            }

            int[] temp = newMat[i];
            newMat[i] = newMat[pivRow];
            newMat[pivRow] = temp;

            double pivot = newMat[i][i];
            for (int j = 0; j < m; j++) {
                newMat[i][j] /= pivot;
            }

            for (int j = 0; j < n; j++) {
                if (j != i) {
                    double multiplier = newMat[j][i];
                    for (int k = 0; k < m; k++) {
                        newMat[j][k] -= multiplier * newMat[i][k];
                    }
                }
            }
        }
        for (int i = 0; i < newMat.length; i++) {
            for (int j = 0; j < newMat[i].length; j++) {
                System.out.print(newMat[i][j]+" ");
            }
            System.out.println(B[i]);
        }
    }
/***Forme Canonique**/
    static String[][] simplextomatrice(String[][] Contraintes,String[] Z,String[] egalite){
        String[][] matrice = Contraintes;
        matrice = Arrays.copyOf(matrice,matrice.length+1);
        matrice[matrice.length-1] = Z;
        int a = 0;
        for (int i = 0 ; i < matrice.length; i++) {
            int j = matrice[i].length;
            int diag = matrice[i].length;
            matrice[i] = Arrays.copyOf(matrice[i],matrice[i].length+Contraintes.length+1);
            while(j < matrice[i].length){
                if(j == matrice[i].length-1 && i <= matrice.length-2){
                    matrice[i][j] = egalite[a];
                    a++;
                }else{
                    if(j-i == diag && i <= matrice.length-2)matrice[i][j] = "1";
                    else matrice[i][j] = "0";
                }
                j++;
            }
        }
        return matrice;
    }

    static int indexMaxZ(String[][] matrix){
        int lastIndex = matrix.length-1;
        int index = 0;
        for (int i = 0; i < matrix[lastIndex].length; i++) {
            if(i<matrix[lastIndex].length-1 && changeIffraction(matrix[lastIndex][i]) > changeIffraction(matrix[lastIndex][index])) index = i;
        }
        return index;
    }

    static int getPivot(String[][] matrix, int indexMaxZ){
        int lastIndex = matrix[0].length-1;
        int index = 0;
        double valeur = 0;
        for (int i = 0; i < matrix.length-1; i++) {
            if(changeIffraction(matrix[i][indexMaxZ]) > 0){
                if(i == 0 || valeur == 0){
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMaxZ]);
                    index = i;
                } else if (i > 0 && changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMaxZ]) < valeur) {
                    valeur = changeIffraction(matrix[i][lastIndex])/changeIffraction(matrix[i][indexMaxZ]);
                    index = i;
                }
            }
        }
        return index;
    }
    static boolean checkFraction(String pivot){
        if(pivot.contains("/")){
            return true;
        }
        return false;
    }

    static boolean checkIfOptimiser(String[][] matrix){
        int lastIndex = matrix.length-1;
        for (int i = 0; i < matrix[lastIndex].length-1; i++) {
            if(changeIffraction(matrix[lastIndex][i]) > 0) return false;
        }
        return true;
    }

    static String changePosition(String pivot){
        String news = String.valueOf(pivot.charAt(2))+String.valueOf(pivot.charAt(1))+String.valueOf(pivot.charAt(0));
        return news;
    }

    static String[][] algoSimplexe(String[][] matrix){
        int column = indexMaxZ(matrix);
        int line = getPivot(matrix,column);
        base[line] = String.valueOf(column);
        double pivot = changeIffraction(matrix[line][column]);
        boolean test = false;
        if(checkFraction(matrix[line][column])){
            pivot = changeIffraction(changePosition(matrix[line][column]));
            test = true;
        }
        for (int i = 0; i < matrix[line].length; i++) {
            double value = 0;
            if(test){
                value = pivot*changeIffraction(matrix[line][i]);
            }else{
                value = changeIffraction(matrix[line][i])/pivot;
            }
            matrix[line][i] = String.valueOf(value);
        }
        for (int i = 0; i < matrix.length; i++) {
            if(i!=line){
                double fraction = changeIffraction(matrix[i][column])/changeIffraction(matrix[line][column]);
                for (int j = 0; j < matrix[i].length; j++) {
                    double temp = changeIffraction(matrix[i][j]);
                    double lignePivot = changeIffraction(matrix[line][j]);
                    temp = temp - fraction*lignePivot;
                    matrix[i][j] = String.valueOf(temp);
                }
            }
        }
        if(!checkIfOptimiser(matrix))matrix = algoSimplexe(matrix);
        return matrix;
    }
}
