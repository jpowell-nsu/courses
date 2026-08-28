#include <iostream>
#include "maze.h"

int main() {
    char maze[5][COLS] = {
        {'S', '.', '.', '#', '.'},
        {'#', '#', '.', '#', '.'},
        {'.', '.', '.', '.', '.'},
        {'.', '#', '#', '#', '.'},
        {'.', '.', '.', '.', 'E'}
    };

    bool visited[5][COLS] = {};

    std::cout << std::boolalpha << hasPath(maze, visited, 5, 0, 0, 4, 4) << std::endl;
 void dfs (char maze, int posy, int posx,  )
 {
    if(i <cols or if i> Cols)
    {
    visited[i] = true; 
    }

    if(i + 1 == '.' )
    {
    visited[i][j]= true;
    dfs(maze, i+1, posy)
    }

    if(i - 1 ='.' )
    {
    visited[i][j]= true;
    dfs(maze, +1, posy)
    }
    
    


 }
    return 0;
}
