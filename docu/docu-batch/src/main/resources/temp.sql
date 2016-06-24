
SELECT TITRE FROM `t_episode` 
GROUP BY (SERIE+SAISON+EPISODE) 
HAVING COUNT(SERIE+SAISON+EPISODE) = 2


SELECT FORMAT, RESOLUTION, TAILLE, DUREE, TAILLE/DUREE/10+1 AS RATIO, TITRE
FROM t_episode 
WHERE (FORMAT IS NOT NULL) AND (DUREE>0)
ORDER BY RATIO ASC

UPDATE t_episode SET QUALITE=TAILLE/DUREE/10+1
WHERE (FORMAT IS NOT NULL) AND (DUREE>0)

UPDATE t_episode SET QUALITE=5
WHERE QUALITE > 5


DELETE FROM T_EPISODE WHERE SERIE = 156;
DELETE FROM T_SERIE WHERE ID = 156;