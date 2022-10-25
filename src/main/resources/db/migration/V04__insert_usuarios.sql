INSERT INTO usuario (nome, email, senha) VALUES
('Barbara', 'barbara@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa'),
('Gabriel', 'gabriel@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa'),
('Michelli', 'michelli@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa'),
('Paulo', 'paulo@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa'),
('Christian', 'christian@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa'),
('João', 'joao@email.com', '$2a$12$JPhb84REciq6x6eb7iDgKeQ8QHZD0yGzkIKnxz112uyVDCwp8kgPa');

INSERT INTO usuario_perfil (id_usuario, id_perfil) VALUES
( (SELECT id FROM usuario WHERE email='barbara@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id FROM usuario WHERE email='gabriel@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id FROM usuario WHERE email='michelli@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id FROM usuario WHERE email='paulo@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') ),
( (SELECT id FROM usuario WHERE email='christian@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='USER') ),
( (SELECT id FROM usuario WHERE email='joao@email.com'),
  (SELECT id_perfil FROM perfil WHERE nome='ADMIN') );