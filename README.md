# JAVA22-java2--slutprojekt-Albin-Jonasson
slutpro
Albin Jonasson									JAVA22





Projektet fokuserar på att skapa en applikation för en produktionsregulator som kombinerar en grafisk användargränssnitt del (GUI) med funktionalitet för loggning. Detta syftar till att övervaka och styra händelser som produceras och konsumeras i realtid

.
Projektet har ett grafiskt gränssnitt som gör det enkelt att använda. Här kan användaren hantera producenter och konsumenter som manipulerar en buffert för lagring av enheter. Knappar finns tillgängliga för att lägga till eller ta bort producenter vilket ger kontroll över produktionsflödet. För att ge överblick visas en progressbar som illustrerar buffertens nuvarande kapacitet jämfört med sin maximala potential.

En integrerad loggfunktion registrerar viktiga händelser i realtid. Det inkluderar anteckningar om när enheter läggs till eller tas bort från bufferten. Dessutom ger den notifieringar när buffertens tillgänglighet når kritiska punkter, till exempel vid låg (under 10%) eller hög (över 90%) buffertkapacitet. Detta gör det möjligt för användaren att snabbt identifiera och agera på eventuella problem eller högproduktiva perioder.


Applikationen är utvecklad i Java med hjälp av Swing-biblioteket för att skapa den grafiska delen. För att logga händelser använder den Log4j-funktionalitet för att spara händelser i en separat fil för enkel åtkomst och referens.


Sammanfattningsvis resulterade projektet i en fungerande produktionsregulator som ger användaren en klar överblick och kontroll över produktionen och konsumtionen av enheter i realtid. Genom den kombinerade användningen av GUI och log funktionen möjliggör en effektiv övervakning, hantering och identifiering av produktionsflödet status.
