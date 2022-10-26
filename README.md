# BGG Client

Java Client for BoardGameGeek API

BGG XML API
* v1: https://boardgamegeek.com/wiki/page/BGG_XML_API
* v2: https://boardgamegeek.com/wiki/page/BGG_XML_API2

| Commands     | Types              | Supported |
|--------------|--------------------|:---------:|
| Thing Items  | boardgame          |    v2     |
| Thing Items  | boardgameexpansion |     X     |
| Thing Items  | boardgameaccessory |     X     |
| Thing Items  | videogame          |     X     |
| Thing Items  | rpgitem            |     X     |
| Thing Items  | rpgissue           |     X     |
| Family Items |                    |     X     |
| Forum Lists  |                    |     X     |
| Forums       |                    |     X     |
| Threads      |                    |     X     |
| Users        |                    |     X     |
| Guilds       |                    |     X     |
| Plays        |                    |    v2     |
| Collection   |                    |    v2     |
| Hot Items    |                    |     X     |
| Geeklist     |                    |    v1     |
| Search       |                    |     X     |

---

### Thing Items
Base URI: /xmlapi2/thing?parameters

| Parameter        | Supported |
|------------------|:---------:|
| id=NNN           |     √     |
| type=THINGTYPE   | boardgame |
| versions=1       |     √     |
| videos=1         |     X     |
| stats=1          |     √     |
| historical=1     |     X     |
| marketplace=1    |     X     |
| comments=1       |     √     |
| ratingcomments=1 |     √     |
| page=NNN         |     √     |
| pagesize=NNN     |     √     |
| from=YYYY-MM-DD  |     X     |
| to=YYYY-MM-DD    |     X     |

---

### Plays
Base URI: /xmlapi2/plays?parameters

| Parameter          | Supported |
|--------------------|:---------:|
| username=NAME      |     √     |
| id=NNN             |     √     |
| type=TYPE          |   thing   |
| mindate=YYYY-MM-DD |     X     |
| maxdate=YYYY-MM-DD |     X     |
| subtype=TYPE       | boardgame |
| page=NNN           |     √     |

---

### Collection
Base URI: /xmlapi2/collection?parameters


| Parameter              | Supported |
|------------------------|:---------:|
| username=NAME          |     √     |
| version=1              |     √     |
| subtype=TYPE           |     √     |
| excludesubtype=TYPE    |     √     |
| id=NNN                 |     √     | 
| brief=1                |     √     |
| stats=1                |     √     |
| own=[0,1]              |     √     |
| rated=[0,1]            |     √     |
| played=[0,1]           |     √     |
| comment=[0,1]          |     √     |
| trade=[0,1]            |     √     |
| want=[0,1]             |     √     |
| wishlist=[0,1]         |     √     |
| wishlistpriority=[1-5] |     √     |
| preordered=[0,1]       |     √     |
| wanttoplay=[0,1]       |     √     |
| wanttobuy=[0,1]        |     √     |
| prevowned=[0,1]        |     √     |
| hasparts=[0,1]         |     √     |
| wantparts=[0,1]        |     √     |
| minrating=[1-10]       |     √     |
| rating=[1-10]          |     √     |
| minbggrating=[1-10]    |     √     |
| bggrating=[1-10]       |     √     |
| minplays=NNN           |     √     |
| maxplays=NNN           |     √     |
| showprivate=1          |     √     |
| collid=NNN             |     √     |
| modifiedsince=YY-MM-DD |     √     |

---

### Geeklist
Base URI: /xmlapi/geeklist/<listid>

| Parameter  | Supported |
|------------|:---------:|
| listid     |     √     |
| comments=1 |     √     |

---
