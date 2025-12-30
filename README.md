Commande powershell :

Invoke-WebRequest -Uri "http://localhost:8080/game/create" -Method POST
Invoke-WebRequest -Uri "http://localhost:8080/game/join" -Method POST -Body '{"id":1}' -ContentType "application/json"

http://localhost:8080/h2-console/
SELECT g.id AS game_id, g.status, p.id AS player_id, p.name
FROM game g
LEFT JOIN player p ON p.game_id = g.id;