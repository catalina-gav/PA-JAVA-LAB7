# PA-JAVA-LAB7
Am avut niste probleme cu internetul si din aceasta cauza nu am pus laboratorul la ora 2
Nu functioneaza intocmai corect programul dar nu am reusit inca sa-mi dau seama de ce.
In schimb am facut o lista in Board care primeste tokenuri(numere intregi) distincte (cu exceptia valorii 0 care poate aparea de mai multe ori si care reprezinta 'blank') -am generat tokenuri si am verificat daca acestea existau deja in lista.Daca existau si nu era 0, generam altul.
pentru a verifica daca este o progresie am spus ca este nevoie de cel putin 2 valori selectate pentru a calcula ratia ca apoi de fiecare data cand aveam o valoare noua verificam daca este egal cu elementul dinaintea lui +  ratia (pentru ca intr-o progresie elementele reprezinta valoarea precedenta din progresie - ratia.In cazul in care o valoare era 0 am setat-o ca fiind deja elementul dinainte+ratia pentru ca 0 e blank si poate fi inlocuit cu orice numar, deci inlocuim cu numarul care ar trebui sa urmeze in progresie.
In run am folosit synchronized(board) deoarece ambii playeri incearca sa acceseze boardul si pot exista conflicte.
Programul ar trebui sa-si incheie executia cand unul dintre jucatori a castigat sau nu mai sunt elemente pe tabla (conditia din while)
