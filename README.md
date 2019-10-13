# WindowSystemFX
**A Window System for JavaFX applications inspired by NetBeans Window System**
![screenshot of created window system](docs/images/demo1.png) 

The setup is configured from annotations and by exposing ServiceProviders.

- A *Window* is a Node and a window is attached to a *Mode*. 
- A *Mode* can be attached to another *Mode*.
- A *Mode* is one of; *SplitPane*, *StackPane* or *TabPane*.

Still pretty early development and much on the todo list such as
- Drag and drop
- Save and restore window states
- Styling
- Undock to Stage
