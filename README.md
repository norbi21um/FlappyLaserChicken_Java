# FlappyLaserChicken_Java
Q-learning

Ebben a házi feladatban a cél egy tanuló ágens implementálása volt, amely képes a Flappy Bird világának sasokkal és lézerfegyverekkel kiegészített változatában minél több pontot összeszedni. 
Ehhez táblázatos Q-tanulás használtam. A feladat leegyszerűsített volt úgy, hogy ne legyen túl nagy az állapottér, így beleférjek az időbe és a memóriába. Egy kiértékelés 1.500.000 tanulási iterációt és 100 epochnyi éles
következtetést jelent. Egy iteráció azt jelenti, hogy az ágens megkapja a legfrissebb játékállapotot, és az alapján visszaadja, hogy mit cselekszik. Esetünkben 0: ha semmit, 1: ha ugrik, és 2: ha lő. 
Ha az ágens "meghal", akkor a pálya előlről kezdődik. 

Egy epoch a pálya elejétől az ágens "haláláig" tart. Minden iteráció után van lehetosége tanulni az ágnesnek, ehhez megkapja az előző állapotot, az új állapotot, a cselekvését és a kapott rewardot. Az epochok és a tanítás végét is függvény jelzi az ágensnek.

A feladathoz a program nagy része adott volt, nekem csupán a Q-tanulást kellett implementálnom. 



![2021-12-23 08_30_32-Window](https://user-images.githubusercontent.com/22506745/147296221-a13758ac-e0c4-45f4-9611-46e476667288.png)
![2021-12-23 08_30_15-Window](https://user-images.githubusercontent.com/22506745/147296227-654d36b1-b46d-4a6d-8667-fad6a815a9cb.png)

