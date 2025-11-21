 Quickstart Instructions

## Installation
- Install Java
- ```java src/Main.java```

## Commands
Commands are entered using the command, followed by the index of the item you want to interact with. **Spaces are required**.

- `d [index]` - Describes the item in mulch (much) detail
  - Example: `d 3` describes the third item
- `i [index]` - Interacts with an item
  - Example: `i 2` walks through corridor #2
- `i [index1] [index2]` - Interacts with an item using another item
  - Example: `i 4 6` interacts with item 4 using item 6
- you can skip using `i` if you want, as a shortcut and just go straight to `[index] [index2]`.

Let's look at an example:
```
████████████  1 : You are in a Research Lab                                                         
██.......@9   2 : Cupboard                                                                          
██........██  3 : Cupboard                                                                          
██........8                                                                                         
██........██                                                                                        
███6 ███████
----------------------------------------------------------------------------------
Inventory contents (1/10):
12:Oxygen Canister

Entering a Research Lab
Oxygen Left: 29
```
- To open the cupboard use `i 2`
- To use the oxygen canister use `i 12`
- To describe the room use `d 1`
- To try open the cupboard using the oxygen canister (which obviously would not work)  use `i 2 12`

Tip: You might want to make a map as you play. It should make the game a whole lot easier (and more fun). 

## Story/Objectives
You work at a super top secret facility. It is Friday and no one is in the office because everyone is working from home. 
Suddenly, while you are in the cleanroom, A gas leak causes experimental poisonous gas to flood the facility. Thankfully, you are wearing a hazmat suit.
The military has already been called to 'dispose' of the situation, but after much convincing by fellow scientists have decided to hold off on destroying the evidence until you have made it out alive.
It is your job to escape, as well as bring back as many research papers and 'gizmos' as you can. 
The item's score is shown beside it, such as `research paper - 50` is worth 50 score at the end of the game.
There is a maximum of 1000. Don't focus on documents too hard, because if you die, you will get no score (and be dead!).

## Testing
- Install Java
- ```java src/Test.java```