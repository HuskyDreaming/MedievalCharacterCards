Here’s the formatted README for **Medieval Character Cards**:

---

# Medieval Character Cards  
![Spigot](https://img.shields.io/badge/Spigot-1.21.X-green.svg)  ![Version](https://img.shields.io/badge/Version-1.2.0-blue.svg)  

**Medieval Character Cards** is a highly customizable plugin for Minecraft servers, providing players with the ability to create detailed character profiles. Designed for roleplay-focused servers, this plugin enhances immersion by allowing users to personalize attributes such as age, gender, title, and more. Whether you’re building a medieval kingdom or running a fantasy-themed server, this plugin adds depth and creativity to your gameplay.


## Features  

- **Character Customization**:  
  Players can set and update attributes like name, age, gender, height, titles, and detailed descriptions.  

- **Command-Based Interaction**:  
  Includes intuitive commands for creating, editing, viewing, and deleting character profiles.  

- **Integration Support**:  
  - PlaceholderAPI compatibility for displaying character data in other plugins.  
  - Configurable settings through `config.yml` for tailored experiences.  

- **Optimized Data Management**:  
  Repository-based architecture for managing character, gender, and title data efficiently.  

- **Extensive Localization**:  
  Full translation support to provide an accessible experience for players worldwide.
  

## Dependencies  

- **JAVA 21+**  
- **PlaceholderAPI** (Optional, for placeholder functionality)

## Installation  

1. Download the latest version of **Medieval Character Cards**.  
2. Place the `.jar` file into your server's `plugins` folder (compatible with Spigot, Paper, or similar platforms).  
3. Restart your server or reload the plugin to activate it (`/reload` or `/restart`).  


## Commands  

The plugin offers the following commands for managing character cards:  

- **/character create**: Create a new character profile.  
- **/character view**: View your or another player's character profile.  
- **/character edit [attribute] [value]**: Edit specific attributes of your character.  
- **/character delete**: Delete your character profile.  

*(Permissions and additional commands can be configured via the plugin settings.)*  


## Configuration  

Adjust the plugin’s settings in `config.yml`:  

```yaml  
# Different language support
language: en-us

# Minimum characters/number limit allowed
min:
  age: 0
  height: 140
  first-name: 3
  middle-name: 3
  last-name: 3
  description: 0

# Maximum characters/number limit allowed
max:
  age: 100
  height: 200
  first-name: 8
  middle-name: 10
  last-name: 12
  description: 128

# Format when viewing a player's character card
# PlaceholderAPI supported!

# Built-in Placeholders:
# <player-name> | Players name
# <age>         | Age
# <first>       | First name
# <middle>      | Middle name
# <last>        | Last name
# <description> | Description
# <title>       | Title (Example Mr, MRS)
# <gender>      | Gender (Example Male, Female)

view-format:
  - ""
  - "         &a<player-name>'s Info         "
  - " &f&o<description>"
  - " &7Title: &f<title>"
  - " &7First: &f<first>"
  - " &7Middle: &f<middle>"
  - " &7Last: &f<last>"
  - " &7Age: &f<age>"
  - " &7Height: &f<height>cm"
  - " &7Gender: &f<gender>"
  - ""
```  



## Authors  

- [@HuskyDreaming](https://github.com/HuskyDreaming)  



## LICENSE  

This plugin is released under the MIT License. See the [LICENSE](LICENSE) file for details.  

  
