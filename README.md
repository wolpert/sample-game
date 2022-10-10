# Sample
This project shows how to the gamelib repository.

# Setup

The project here is setup to function like a normal libGDX project.
If you supported android you would have an android directory like you
do a desktop. (I'll add one example soon.) Core has the main game files.

# How to run examples

There four setup examples.

## Default libGDX project
```shell
gradle :desktop:run
```

This setup basically does one thing, uses Dagger as the client-side
injection library. And the result is the default libGDX startup screen.

## Entity only project
```shell
gradle :desktop:entity
```

This is similar to the libGDX default project, except the components are
rendered using the Ashley entity library. (Ashley is used in the other examples too.)


## Using the hex library
```shell
gradle :desktop:hex
```

This shows how to do a layout with the Nex library. The library was model
pretty heavy on the article from the [RedBlob Game's hexagons project.](http://www.redblobgames.com/grids/hexagons/)
I've used this twice and it's pretty good.


## Using box2d
```shell
gradle :desktop:box2d
```

This shows how to setup a physics engine in your game, with Ashley and Dagger.

## Coming soon!

### NetBattle sample

Building out an example using the gamelibNet client.
