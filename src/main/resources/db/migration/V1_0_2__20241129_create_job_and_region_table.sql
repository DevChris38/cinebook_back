CREATE TABLE job
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    code      VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

INSERT INTO job (code, full_name)
VALUES ('ACCESSOIRISTE', 'Accessoiriste'),
       ('ACTEUR', 'Acteur / Actrice'),
       ('ADM_PROD', 'Administrateur / Administratrice de production'),
       ('ANIMATEUR', 'Animateur / Animatrice 2D/3D'),
       ('ASS_PROD', 'Assistant(e) de production'),
       ('ASS_MONTEUR', 'Assistant(e) monteur / monteuse'),
       ('ASS_REAL', 'Assistant(e) réalisateur / réalisatrice'),
       ('BRUIT', 'Bruiteur / Bruiteuse'),
       ('CADR', 'Cadreur / Cadreuse ou opérateur / opératrice de prise de vues'),
       ('CANT', 'Cantinier / Cantinière'),
       ('CASC', 'Cascadeur / Cascadeuse'),
       ('CHAR_PROD', 'Chargé(e) de production'),
       ('ELEC', 'Chef-électricien(ne) et électricien(ne)'),
       ('MACH', 'Chef-machiniste et machiniste'),
       ('SON', 'Chef-opérateur / opératrice du son ou Ingénieur(e) du son'),
       ('DIR_PHO', 'Directeur / Directrice de la photographie'),
       ('CONS_TECH', 'Conseiller(e) technique'),
       ('COIF', 'Coiffeur / Coiffeuse'),
       ('COOR_INTIM', 'Coordinateur / Coordinatrice d\'intimité'),
       ('COST', 'Costumier(e)'),
       ('CHEF_OPP', 'Chef décorateur / décoratrice'),
       ('DIAG', 'Dialoguiste'),
       ('DIR_ART', 'Directeur / Directrice artistique'),
       ('DIR_CAST', 'Directeur / Directrice de casting'),
       ('DIR_PROD', 'Directeur / Directrice de production'),
       ('DIR_POST_PROD', 'Directeur / Directrice de postproduction ou Superviseur / Superviseuse de postproduction'),
       ('DIR_TECH', 'Directeur / Directrice technique'),
       ('DISTRIB', 'Distributeur / Distributrice'),
       ('EDIT', 'Editeur / Editrice DVD ou VOD'),
       ('ETAL', 'Etalonneur / Etalonneuse'),
       ('EXPLOIT', 'Exploitant(e) de salle de cinéma'),
       ('FIG', 'Figurant(e)'),
       ('GROUP', 'Groupman / Groupwoman ou Groupiste'),
       ('HAB', 'Habilleur / Habilleuse'),
       ('INGE_VIS', 'Ingénieur(e) de la vision'),
       ('MAQ', 'Maquilleur / Maquilleuse'),
       ('MIX', 'Mixeur / Mixeuse'),
       ('MONT', 'Monteur / Monteuse ou Chef-monteur / Chef-monteuse'),
       ('MONT_SON', 'Monteur / Monteuse son'),
       ('MUSI', 'Musicien(ne)'),
       ('STEAD', 'Operateur / Opératrice steadicam ou steadicameur / Steadicameuse'),
       ('PERCH', 'Perchman / Perchwoman'),
       ('PHOTO', 'Photographe de plateau'),
       ('ASSIST_OPE', 'Assistant(e) operateur / operatrice ou pointeur / pointeuse'),
       ('PROD', 'Producteur / Productrice'),
       ('PROJ', 'Projectionniste'),
       ('REAL', 'Realisateur / Realisatrice'),
       ('REG_GEN', 'Regisseur / Regisseuse général(e)'),
       ('RESP_DOUBL', 'Responsable du doublage'),
       ('RESP_TECH', 'Responsable technique'),
       ('SCEN', 'Scénariste'),
       ('SCRIPT', 'Scripte'),
       ('TECH_MAINT', 'Technicien(ne) de maintenance'),
       ('TRUC', 'Truqueur / Truqueuse'),
       ('VENT', 'Ventouseur / Ventouseuse'),
       ('VEND', 'Vendeur / Vendeuse international');

CREATE TABLE region
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    code      VARCHAR(255) NOT NULL,
    full_name VARCHAR(255) NOT NULL
);

INSERT INTO region (code, full_name)
VALUES ('REGION_ARA', 'Auvergne-Rhône-Alpes'),
       ('REGION_BFC', 'Bourgogne-Franche-Comté'),
       ('REGION_BRE', 'Bretagne'),
       ('REGION_CVL', 'Centre-Val de Loire'),
       ('REGION_20R', 'Corse'),
       ('REGION_GES', 'Grand Est'),
       ('REGION_HDF', 'Hauts-de-France'),
       ('REGION_IDF', 'Île-de-France'),
       ('REGION_NOR', 'Normandie'),
       ('REGION_NAQ', 'Nouvelle-Aquitaine'),
       ('REGION_OCC', 'Occitanie'),
       ('REGION_PDL', 'Pays de la Loire'),
       ('REGION_PAC', 'Provence-Alpes-Côte d\'Azur'),
       ('REGION_GLP', 'Guadeloupe'),
       ('REGION_GUF', 'Guyane'),
       ('REGION_MTQ', 'Martinique'),
       ('REGION_REU', 'La Réunion'),
       ('REGION_MYT', 'Mayotte');

CREATE TABLE customer_job
(
    customer_id INT NOT NULL,
    job_id      INT NOT NULL,
    PRIMARY KEY (customer_id, job_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (job_id) REFERENCES job (id)
);

CREATE TABLE customer_region
(
    customer_id INT NOT NULL,
    region_id   INT NOT NULL,
    PRIMARY KEY (customer_id, region_id),
    FOREIGN KEY (customer_id) REFERENCES customer (id),
    FOREIGN KEY (region_id) REFERENCES job (id)
);