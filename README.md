## What is Spantasy League?

The name Spantasy League is a play on words on [Fantasy Sport](https://en.wikipedia.org/wiki/Fantasy_sport "Wikipedia Fantasy Sport"). More specifically, it has a [point system](#point-system) similar to that of Football (Soccer).

## How can I use it?

### Clone GitHub repository

```bash
git clone https://github.com/MichailDeWet/spantasy-league-app.git
```

### Build and run with Maven

```bash
mvn clean package
... (Wait upon build completion) ...
cd target
java -jar spantasy-league-app-1.0-SNAPSHOT.jar
```
###### From repository root directory

## Behaviour

When making use of files as input, please put the file in the resource folder (*src/main/resources/*) and save it as a text `*.txt` file.

### Sample Input

Lions 3, Snakes 3\
Tarantulas 1, FC Awesome 0\
Lions 1, FC Awesome 1\
Tarantulas 3, Snakes 1\
Lions 4, Grouches 0

---

When entering results manually, please enter ***end*** after the last result to indicate the end of manual input.

### Sample Input (manual input)

Lions 3, Snakes 3\
Tarantulas 1, FC Awesome 0\
Lions 1, FC Awesome 1\
Tarantulas 3, Snakes 1\
Lions 4, Grouches 0\
end

---

### Expected Output

`1.` Tarantulas, 6 pts\
`2.` Lions, 5 pts\
`3.` FC Awesome, 1 pt\
`3.` Snakes, 1 pt\
`5.` Grouches, 0 pts

Note: [Why](#how-are-teams-ranked) do FC Awesome and Snakes share a rank?

## Point System

**`Win` : 3 points**\
**`Draw` : 1 point**\
**`Loss` : 0 points**

### How are teams ranked?

Primarily, teams are ranked based on their accumulated points, respectively, in descending order.\
In the occurrence where teams are level on points, they share a rank, however, the table will list the affected teams in alphabetic order.