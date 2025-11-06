# Quickstart Instructions

## Installation
1. Install Java
2.
```bash
java src/Main.java
```

2. Install IntelliJ (Community Edition is free)
   - Open project
   - Open Main.java
   - Click run button in top right

## Game Overview
This is my awesome game about exploring a temple.

## Commands
Commands are entered using the command, followed by the index of the item you want to interact with. **Spaces are required**.

- `d [index]` - Describes the item in mulch (much) detail
  - Example: `d 3` describes the third item
- `i [index]` - Interacts with an item
  - Example: `i 2` walks through corridor #2
- `i [index1] [index2]` - Interacts with an item using another item
  - Example: `i 4 6` interacts with item 4 using item 6

## Current Status
- Map generation is complete
- Console displays map (@ represents player)
- Each room contains a brazier

## Todo List
- [x] Map generation
- [ ] Random structure + item generation
- [ ] Vault room (lever/door mechanism)
- [ ] Map generation wrapping corridors
- [ ] Exit strategy
- [ ] Polish
- [ ] Proper testing
