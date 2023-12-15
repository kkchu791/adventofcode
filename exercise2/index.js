const fs = require("fs");
// Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green -> Valid
// Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue -> Valid
// Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red -> Invalid
// Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red -> Invalid
// Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green -> Valid

// Constraints: 12 red cubes, 13 green cubes, and 14 blue cubes
// Parse game IDs
// Parse everything after colon; split things after ;
// // each set of strings, we need to gather the count of cubes by color
// Hashmap: key = game ID, value = hashmap storing max number of color in cube
// IE:
// const gameMap = {
//   1: {
//     red: 4,
//     blue: 6,
//     green: 2,
//   },
// };

// Loop over gameMap, and compare to constraints. So if it passes, then add the game ID to sum
const getValidGameSum = () => {
  const contents = fs
    .readFileSync("./message.txt", "utf-8")
    .replace(/\r/g, "")
    .split("\n");
  const gameMap = {};
  const regex = /\d+/;

  contents.forEach((game) => {
    const gameId = game.match(regex)[0];
    gameMap[gameId] = {
      red: 0,
      blue: 0,
      green: 0,
    };

    game
      .split(": ")[1]
      .split("; ")
      .forEach((set) => {
        set.split(", ").forEach((cubeColor) => {
          const splitCubeColor = cubeColor.split(" ");
          gameMap[gameId][splitCubeColor[1]] = Math.max(
            gameMap[gameId][splitCubeColor[1]],
            parseInt(splitCubeColor[0])
          );
        });
      });
  });

  let result = 0;

  for (const [gameId, cubeColorMap] of Object.entries(gameMap)) {
    if (cubeColorMap["red"] > 12) {
      continue;
    }
    if (cubeColorMap["green"] > 13) {
      continue;
    }
    if (cubeColorMap["blue"] > 14) {
      continue;
    }

    result += parseInt(gameId);
  }

  return result;
};

console.log(getValidGameSum());
