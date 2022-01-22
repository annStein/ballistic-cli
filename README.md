# Ballistic CLI

This is a solution of the computerwoman challenge @Femhack by Nuwe.io.<br>
The task was a simple CLI to calculate ballistic trajectories.

## Getting Started

Clone the repository and run the application.<br>

If results are to be saved later, the output path should be defined first if necessary (command: `change-out-path <new-path>`)<br>

## Available Commands

| command | explanation |
| ------------- | ------------- |
|out-path | Returns the current path where results are stored.|
|change-out-path | Changes the output path for results.|
|compute-file | Computes the maximum height of the projectile and the maximum traveled distance. Input via JSON-File (path).|
|compute-manual | Computes the maximum height of the projectile and the maximum traveled distance. Input via args.|
|h-max | Computes the maximum height of the projectile.|
|x-max | Computes the maximum traveled distance.|
|help | Returns all commands and explanations |

Please note: args must be entered separated by spaces. To write the compute results to files add `--to-file`.<br>
To receive more help for specific commands type `help <command>`.


## Error Information

This CLI has user friendly error messages. Whenever you want to debug the app, the actual errors are written in a
logfile
(computerwoman.log).
