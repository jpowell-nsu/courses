#ifndef COIN_CHANGE_H
#define COIN_CHANGE_H

// Returns the number of ways to make amount using the given coins,
// starting the search at coins[index]. Each coin may be used any number
// of times, and the order coins are picked in does not matter.
//
// TODO: implement this function, including the base cases we worked out
// as a class.
int countChange(int amount, int coins[], int numCoins, int index);

#endif
