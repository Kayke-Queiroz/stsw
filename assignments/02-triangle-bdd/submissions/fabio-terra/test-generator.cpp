#include <bits/stdc++.h>
using namespace std;

int main (void) {
    for (int i = 0; i <= 201; i++){
        if (i == 2) i = 100;
        if (i == 101) i = 199;

        for (int j = 0; j <= 201; j++){
            if (j == 2) j = 100;
            if (j == 101) j = 199;

            for (int k = 0; k <= 201; k++){
                if (k == 2) k = 100;
                if (k == 101) k = 199;

                cout << "\""<< i << ", " << j << ", " << k << ", ";
                if (i <= 0 || j <= 0 || k <= 0 || i > 200 || j > 200 || k > 200) {
                    cout << "Invalido\"";
                } else if (i >= j + k || j >= i + k || k >= i + j) {
                    cout << "Nao e um triangulo\"";
                }else if (i == j && j == k) {
                    cout << "Equilatero\"";
                } else if (i == j || i == k || j == k) {
                    cout << "Isosceles\"";
                } else {
                    cout << "Escaleno\"";
                }
                cout << "," << endl;
            }
            cout << "\n";
        }
        cout << "\n";
    }
    return 0;
}
