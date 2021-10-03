# FrozenActions

```yaml
#   Available Modifiers:
#    - [CHANCE=<chance>]
#    - [DELAY=<delay>]
#    - [ACTION] Arg1;Arg2;Arg3
#
#  Example Action with both modifiers:
#    - [CHANCE=30][DELAY=5s][MESSAGE] &aYou had a &f30% &achance of getting this message, with a &f3s &adelay!
#
# Player:
# • [CENTERMESSAGE] <message>
# • [ACTIONBARMESSAGE] <message>
# • [TITLEMESSAGE] <title;subtitle;fadein;stay;fadeout>
# • [SOUND] <sound;volume;pitch>
# • [BUNGEE] <server>````
# • [TELEPORT] <world;x;y;z;yaw;pitch>
# • [CONSOLECOMMAND] <command>
# • [PLAYERCOMMAND] <command>
# • [MESSAGE] <message>
# • [GAMEMODE] <gamemode>
# • [POTIONEFFECT] <effect;duration(ticks);amplitude>
# • [PARTICLE] <particle;count;offsetX;offsetY;offsetZ;extra>
# • [HEALTH] <[+/-]value>
# • [FOODLEVEL] <[+/-]value>
#
# Everyone:
# • [JSONMESSAGE] <JSON message>
# • [JSONMESSAGE] <JSON message>
# • [CENTERBROADCAST] <message>
# • [ACTIONBARBROADCAST] <message>
# • [TITLEBROADCAST] <title;subtitle;fadein;stay;fadeout>
# • [SOUNDBROADCAST] <sound;volume;pitch>
# • [BROADCAST] <message>
#
# Examples:
# • [JSONBROADCAST] {"text":"Go to Google","color":"red","clickEvent":{"action":"open_url","value":"google.com"}}
# • [JSONMESSAGE] {"text":"Go to Google","color":"red","clickEvent":{"action":"open_url","value":"google.com"}}
# • [CENTERBROADCAST] &bThis is centered for everyone
# • [CENTERMESSAGE] &bThis is centered
# • [ACTIONBARBROADCAST] &cEveryone sees this in the actionbar
# • [ACTIONBARMESSAGE] &cThis shows in the actionbar
# • [TITLEMESSAGE] Hello;%player_name%;20;60;20
# • [TITLEBROADCAST] Hello;Players;20;60;20
# • [SOUNDBROADCAST] ENTITY_ARROW_HIT_PLAYER;2;2
# • [SOUND] ENTITY_ARROW_HIT_PLAYER;2;2
# • [BUNGEE] lobby
# • [TELEPORT] world;0;60;0;90;90
# • [CONSOLECOMMAND] eco give %player_name% 1000
# • [PLAYERCOMMAND] balance
# • [BROADCAST] &aHello Players
# • [MESSAGE] &cHello &2World
# • [GAMEMODE] creative
# • [POTIONEFFECT] glowing;200;5
# • [PARTICLE] DOLPHIN;5;0;0;0;0
# • [HEALTH] 10
# • [HEALTH] -2
# • [FOODLEVEL] 10
# • [FOODLEVEL] -2
```
