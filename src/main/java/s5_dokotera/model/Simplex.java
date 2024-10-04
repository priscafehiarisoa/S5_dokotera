package s5_dokotera.model;

import java.util.Arrays;

public class Simplex
{
    public String[] baseDeuxPhases = new String[]{"5", "6", "7", "Z", "A"};
    public String[] bazy = new String[0];
    public double[] Z ;
    public double[][] inequation ;
    public double[] sommeArtificielle ;

    public double[][] matriceDeuxPhase()
    {
        double[][] matriceDeuxPhase = new double[0][];
        for (int i = 0; i < inequation.length; i++)
        {
            matriceDeuxPhase = Arrays.copyOf(matriceDeuxPhase, matriceDeuxPhase.length + 1);
            matriceDeuxPhase[i] = inequation[i];
        }
        Arrays.copyOf(matriceDeuxPhase, matriceDeuxPhase.length + 1);
        matriceDeuxPhase[matriceDeuxPhase.length - 1] = Z;
        Arrays.copyOf(matriceDeuxPhase, matriceDeuxPhase.length + 1);
        matriceDeuxPhase[matriceDeuxPhase.length - 1] = sommeArtificielle;
        return matriceDeuxPhase;
    }

    int indexMinA(double[][] matrix)
    {
        int lastIndex = matrix.length-1;
        int index = 0;
        for (int i = 0; i < matrix[lastIndex].length; i++)
        {
            if (i < matrix[lastIndex].length - 1 && matrix[lastIndex][i] < matrix[lastIndex][index]) index = i;
        }
        return index;
    }

    int getPivotdeuxPhases(double[][] matrix, int indexMinA)
    {
        int lastIndex = matrix[0].length - 1;
        int indexZ = matrix.length - 2;
        int index = 0;
        double valeur = 0;
        for (int i = 0; i < matrix.length - 1; i++)
        {
            if (matrix[i][indexMinA] > 0 && i != indexZ)
            {
                if (i == 0 || valeur == 0)
                {
                    valeur = matrix[i][lastIndex] / matrix[i][indexMinA];
                    index = i;
                }
                else if (i > 0 && matrix[i][lastIndex] / matrix[i][indexMinA] < valeur)
                {
                    valeur = matrix[i][lastIndex] / matrix[i][indexMinA];
                    index = i;
                }
            }
        }
        return index;
    }

    int getPivotMin(double[][] matrix, int indexMinZ)
    {
        int lastIndex = matrix[0].length - 1;
        int index = 0;
        double valeur = 0;
        for (int i = 0; i < matrix.length - 1; i++)
        {
            if (matrix[i][indexMinZ] > 0)
            {
                if (i == 0 || valeur == 0)
                {
                    valeur = matrix[i][lastIndex] / matrix[i][indexMinZ];
                    index = i;
                }
                else if (i > 0 && matrix[i][lastIndex] / matrix[i][indexMinZ] < valeur)
                {
                    valeur = matrix[i][lastIndex] / matrix[i][indexMinZ];
                    index = i;
                }
            }
        }
        return index;
    }

    boolean checkIfSommeartnull(double[][] matrix) throws Exception {
        int lastIndex = matrix.length - 1;
        int lastIndexline = matrix[lastIndex].length - 1;
        for (int i = 0; i<matrix[lastIndex].length-1; i++) {
            if(matrix[lastIndex][i] < 0 && matrix[lastIndex][lastIndexline] == 0) throw new Exception("Simplex Iresoluble");
            else if(matrix[lastIndex][i] < 0) return false;
        }
        return true;
    }

    public double[][] algoSimplexedeuxphases(double[][] matrix)
    {
        int column = indexMinA(matrix);
        int line = getPivotdeuxPhases(matrix, column);
        baseDeuxPhases[line] = String.valueOf(column);
        double pivot = matrix[line][column];
        boolean test = false;
        for (int i = 0; i < matrix[line].length; i++)
        {
            double value = 0;
            if (test)
            {
                value = pivot * matrix[line][i];
            }
            else
            {
                value = matrix[line][i] / pivot;
            }
            matrix[line][i] = value;
        }
        for (int i = 0; i < matrix.length; i++)
        {
            if (i != line)
            {
                double fraction = matrix[i][column] / matrix[line][column];
                for (int j = 0; j < matrix[i].length; j++)
                {
                    double temp = matrix[i][j];
                    double lignePivot = matrix[line][j];
                    temp = temp - fraction * lignePivot;
                    matrix[i][j] = temp;
                }
            }
        }
        try
        {
            if (!checkIfSommeartnull(matrix))
            {
                matrix = algoSimplexedeuxphases(matrix);
            }
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return matrix;
    }

    static int[] getArtificial(double[][] matrix)
    {
        int lastIndex = matrix.length - 1;
        int indexZ = matrix[0].length - 2;
        int[] indice = new int[0];
        int index = 0;
        for (int i = 0; i < matrix[lastIndex].length - 1; i++)
        {
            if (matrix[lastIndex][i] == 1 || i == indexZ)
            {
                Arrays.copyOf(indice, indice.length + 1);
                indice[index] = i;
                index++;
            }
        }
        return indice;
    }

    boolean contenir(int[] tableau, int valeur)
    {
        for (int i : tableau) {
            if (i == valeur) {
                return true;
            }
        }
        return false;
    }

    public double[][] withoutArtificial(double[][] matrix)
    {
        int[] skipColummns = getArtificial(matrix);
        int skipRow = matrix.length - 1;
        double[][] newArray = new double[matrix.length - 1][];
        int k = 0;
        for (int i = 0; i < matrix.length; i++)
        {
            if (i == skipRow)
            {
                continue;
            }
            Arrays.copyOf(bazy, bazy.length + 1);
            bazy[k] = baseDeuxPhases[i];
            k++;
            int newColumn = 0;
            newArray[i < skipRow ? i : i - 1] = new double[matrix[i].length - skipColummns.length];
            for (int j = 0; j < matrix[i].length; j++)
            {
                if (contenir(skipColummns, j))
                {
                    continue;
                }
                newArray[i < skipRow ? i : i - 1][newColumn++] = matrix[i][j];
            }
        }
        int lastRow = newArray.length - 1;
        for (int i = 0; i < newArray[lastRow].length; i++)
        {
            double change = 0;
            if (newArray[lastRow][i] != 0) change = newArray[lastRow][i] * -1;
            newArray[lastRow][i] = change;
        }
        return newArray;
    }

    boolean checkIfMinimiser(double[][] matrix)
    {
        int lastIndex = matrix.length - 1;
        for (int i = 0; i < matrix[lastIndex].length - 1; i++)
        {
            if (matrix[lastIndex][i] < 0) return false;
        }
        return true;
    }

    public double[][] algoSimplexeMin(double[][] matrix)
    {
        int column = indexMinA(matrix);
        int line = getPivotMin(matrix, column);
        bazy[line] = String.valueOf(column);
        double pivot = matrix[line][column];
        boolean test = false;
        for (int i = 0; i < matrix[line].length; i++)
        {
            double value = 0;
            if (test)
            {
                value = pivot * matrix[line][i];
            }
            else
            {
                value = matrix[line][i] / pivot;
            }
            matrix[line][i] = value;
        }
        for (int i = 0; i < matrix.length; i++)
        {
            if (i != line)
            {
                double fraction = matrix[i][column] / matrix[line][column];
                for (int j = 0; j < matrix[i].length; j++)
                {
                    double temp = matrix[i][j];
                    double lignePivot = matrix[line][j];
                    temp = temp - fraction * lignePivot;
                    matrix[i][j] = temp;
                }
            }
        }

        try
        {
            if (!checkIfMinimiser(matrix)) matrix = algoSimplexeMin(matrix);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
        return matrix;
    }

    public String[] getBaseDeuxPhases() {
        return baseDeuxPhases;
    }

    public void setBaseDeuxPhases(String[] baseDeuxPhases) {
        this.baseDeuxPhases = baseDeuxPhases;
    }

    public String[] getBazy() {
        return bazy;
    }

    public void setBazy(String[] bazy) {
        this.bazy = bazy;
    }

    public double[] getZ() {
        return Z;
    }

    public void setZ(double[] z) {
        Z = z;
    }

    public double[][] getInequation() {
        return inequation;
    }

    public void setInequation(double[][] inequation) {
        this.inequation = inequation;
    }

    public double[] getSommeArtificielle() {
        return sommeArtificielle;
    }

    public void setSommeArtificielle(double[] sommeArtificielle) {
        this.sommeArtificielle = sommeArtificielle;
    }
}
