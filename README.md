# ADA T1 - Reaching the Crystal Castle

A dynamic programming problem developed for an **Algorithms / Algorithm Design and Analysis** course.

The objective is to count the number of valid paths through a constrained grid while respecting obstacles, movement restrictions, consecutive-jump limits, and a global jump limit.

---

## 📌 Problem Overview

The explorer **Ava** starts in the top-left corner of a grid and wants to reach the **Crystal Castle**, located in the bottom-right corner.

Ava can perform five different movements:

| Move | Description |
|------|-------------|
| `R` | Move one tile to the right |
| `D` | Move one tile down |
| `LD` | Jump diagonally left and down |
| `DD` | Jump vertically downward |
| `RD` | Jump diagonally right and down |

Ava cannot jump indefinitely.

Two additional restrictions are imposed:

- `M` — maximum number of **consecutive jumps**
- `N` — maximum number of **total jumps** during the entire path

The goal is to calculate the number of valid paths from `(0, 0)` to `(R-1, C-1)`.

Because the number of possible paths can become very large, the result is returned modulo:

```text
1,000,000,007
```

---

## 🗺️ Grid Tiles

Each position in the grid contains one of the following characters:

| Tile | Meaning |
|------|---------|
| `.` | No movement restrictions |
| `X` | Diagonal jumps (`LD`, `RD`) are forbidden |
| `J` | All jumps (`LD`, `DD`, `RD`) are forbidden |
| `#` | Blocked tile — cannot be entered |

The starting and destination tiles are guaranteed to be accessible.

---

## 🧠 Algorithmic Approach

A simple 2D dynamic programming solution is not sufficient because reaching the same cell can represent several different states.

The validity of future movements depends not only on the current position, but also on:

- the number of jumps already used;
- the number of consecutive jumps immediately before reaching the cell.

A suitable DP state can therefore be represented conceptually as:

```text
dp[row][column][totalJumps][consecutiveJumps]
```

where each state stores the number of valid ways of reaching that configuration.

From each state, the algorithm considers every valid movement while checking:

1. Grid boundaries
2. Blocked destination tiles
3. Restrictions imposed by the current tile
4. Maximum consecutive jumps
5. Maximum total jumps

Normal moves reset the consecutive-jump counter, while jump movements increment both jump counters.

---

## ⚙️ Constraints

```text
1 ≤ T ≤ 20
1 ≤ R ≤ 400
1 ≤ C ≤ 400
1 ≤ M ≤ 5
1 ≤ N ≤ 10
```

---

## 📥 Input

The first line contains:

```text
T
```

the number of test cases.

Each test case begins with:

```text
R C M N
```

where:

- `R` — number of rows
- `C` — number of columns
- `M` — maximum consecutive jumps
- `N` — maximum total jumps

The following `R` lines contain the grid.

### Example

```text
3
3 4 1 3
.X.J
..#.
#...
3 4 2 3
.X.J
..#.
#...
10 20 5 10
....................
....................
....................
....................
....................
....................
....................
....................
....................
....................
```

---

## 📤 Output

For every test case, output the number of valid paths from the top-left corner to the bottom-right corner modulo `1,000,000,007`.

### Example

```text
12
15
140916123
```

---

## 🔍 Example

For the first sample:

```text
3 4 1 3

.X.J
..#.
#...
```

Ava may perform at most:

```text
1 consecutive jump
3 jumps in total
```

There are **12 valid paths**.

Some examples are:

```text
D R D R R
D R RD R
D RD R R
R D D R R
R DD R R
R R RD D
RD D R R
```

If the maximum number of consecutive jumps is increased from `1` to `2`, three additional paths become possible, increasing the result from **12 to 15**.

---

## 💡 Key Concepts

This project exercises several important algorithmic concepts:

- **Dynamic Programming**
- **State-space modelling**
- **Grid traversal**
- **Constraint handling**
- **Path counting**
- **Memory optimisation**
- **Modular arithmetic**
- **Algorithmic complexity analysis**

---

## 📊 Complexity

Using a state containing:

```text
(row, column, total jumps, consecutive jumps)
```

the number of states is bounded by approximately:

```text
O(R × C × N × M)
```

Each state has only a constant number of possible transitions (`5`), giving an overall time complexity of approximately:

```text
O(R × C × N × M)
```

With:

```text
R, C ≤ 400
N ≤ 10
M ≤ 5
```

this approach remains practical.

Memory consumption depends on whether the complete DP grid or only the necessary rows/states are retained.

---

## 🎯 What I Learned

This project focuses on translating a complex problem statement into an efficient state representation.

It demonstrates how movement constraints can require additional dimensions in a dynamic programming state, while still keeping the solution manageable by exploiting the small limits on `M` and `N`.

It also reinforces the importance of correctly modelling transitions when different cells impose different movement restrictions.

---

## 👤 Author

**Luís Santos Pereira**  
Computer Science / Computer Engineering Student — NOVA School of Science and Technology

GitHub: https://github.com/imLdss
