/*Borro las tablas y los datos que hubiesen previamente*/
DELETE FROM users;
DELETE FROM ratings;
DELETE FROM favorites;
DELETE FROM movies;


/*Inserto los datos en las tablas creadas*/
INSERT INTO movies (title, original_title, poster_path, release_date, overview) VALUES 
    ('Aquaman and the Lost Kingdom', 'Aquaman and the Lost Kingdom', 'https://image.tmdb.org/t/p/w500/7lTnXOy0iNtBAdRP3TZvaKJ77F6.jpg', '2023-12-20', 'Black Manta, still driven by the need to avenge his father''s death and wielding the power of the mythic Black Trident, will stop at nothing to take Aquaman down once and for all. To defeat him.'),
    ('Wonka', 'Wonka', 'https://image.tmdb.org/t/p/w500/qhb1qOilapbapxWQn9jtRCMwXJF.jpg', '2023-12-06', 'Willy Wonka – chock-full of ideas and determined to change the world one delectable bite at a time – is proof that the best things in life begin with a dream, and if you’re lucky enough to meet Willy Wonka, anything is possible.'),
    ('Migration', 'Migration', 'https://image.tmdb.org/t/p/w500/ldfCF9RhR40mppkzmftxapaHeTo.jpg', '2023-12-06', 'After a migrating duck family alights on their pond with thrilling tales of far-flung places, the Mallard family embarks on a family road trip, from New England, to New York City, to tropical Jamaica.'),
    ('Wish', 'Wish', 'https://image.tmdb.org/t/p/w500/AcoVfiv1rrWOmAdpnAMnM56ki19.jpg', '2023-11-13', 'Asha, a sharp-witted idealist, makes a wish so powerful that it is answered by a cosmic force – a little ball of boundless energy called Star. Together, Asha and Star confront a most formidable foe - the ruler of Rosas, King Magnifico'),
    ('The Marvels', 'The Marvels', 'https://image.tmdb.org/t/p/w500/9GBhzXMFjgcZ3FdR9w3bUMMTps5.jpg', '2023-11-08', 'Carol Danvers, aka Captain Marvel, has reclaimed her identity from the tyrannical Kree and taken revenge on the Supreme Intelligence. But unintended consequences see Carol shouldering the burden of a destabilized universe..'),
    ('Sixty Minutes', '60 Minuten', 'https://image.tmdb.org/t/p/w500/cND79ZWPFINDtkA8uwmQo1gnPPE.jpg', '2024-01-19', 'Desperate to keep custody of his daughter, a mixed martial arts fighter abandons a big match and races across Berlin to attend her birthday party.'),
    ('The Beekeeper', 'The Beekeeper', 'https://image.tmdb.org/t/p/w500/A7EByudX0eOzlkQ2FIbogzyazm2.jpg', '2024-01-10', 'One man’s campaign for vengeance takes on national stakes after he is revealed to be a former operative of a powerful and clandestine organization known as Beekeepers.'),
    ('Patriots Time', 'Время патриотов', 'https://image.tmdb.org/t/p/w500/A4QM6oF4UNNhQh1f9pRdXvyKW5s.jpg', '2022-04-28', 'The brothers Sultan and Bekzat Ibrayev are serving faithfully in the Armed Forces of Kazakhstan, and at the same time they are in family disagreement.'),
    ('Radical', 'Radical', 'https://image.tmdb.org/t/p/w500/eSatbygYZp8ooprBHZdb6GFZxGB.jpg', '2023-10-19', 'In a Mexican border town plagued by neglect, corruption, and violence, a frustrated teacher tries a radical new method to break through his students’ apathy and unlock their curiosity, their potential… and maybe even their genius.'),
    ('Attack', 'Attack', 'https://image.tmdb.org/t/p/w500/5jGKbYuZtdxSNOocI6ZziQeiY4n.jpg', '2022-04-01', 'With the Parliament under siege, India’s first super soldier Arjun Shergill is tasked to get hold of the terrorists in the nick of time, save the Prime Minister from their clutches and stop a dirty bomb from exploding and destroying Delhi. Will Arjun succeed in his mission?'),
    ('Napoleon', 'Napoleon', 'https://image.tmdb.org/t/p/w500/vcZWJGvB5xydWuUO1vaTLI82tGi.jpg', '2023-11-22', 'An epic that details the checkered rise and fall of French Emperor Napoleon Bonaparte and his relentless journey to power through the prism of his addictive, volatile relationship with his wife, Josephine.'),
    ('Society of the Snow', 'La sociedad de la nieve', 'https://image.tmdb.org/t/p/w500/2e853FDVSIso600RqAMunPxiZjq.jpg', '2023-12-13', 'On October 13, 1972, Uruguayan Air Force Flight 571, chartered to take a rugby team to Chile, crashes into a glacier in the heart of the Andes.'),
    ('Lift', 'Lift', 'https://image.tmdb.org/t/p/w500/gma8o1jWa6m0K1iJ9TzHIiFyTtI.jpg', '2024-01-10', 'An international heist crew, led by Cyrus Whitaker, race to lift $500 million in gold from a passenger plane at 40,000 feet.'),
    ('Badland Hunters', '황야', 'https://image.tmdb.org/t/p/w500/zVMyvNowgbsBAL6O6esWfRpAcOb.jpg', '2024-01-26', 'After a deadly earthquake turns Seoul into a lawless badland, a fearless huntsman springs into action to rescue a teenager abducted by a mad doctor.'),
    ('Anyone But You', 'Anyone But You', 'https://image.tmdb.org/t/p/w500/yRt7MGBElkLQOYRvLTT1b3B1rcp.jpg', '2023-12-21', 'After an amazing first date, Bea and Ben’s fiery attraction turns ice cold — until they find themselves unexpectedly reunited at a destination wedding in Australia. So they do what any two mature adults would do: pretend to be a couple.'),
    ('Role Play', 'Role Play', 'https://image.tmdb.org/t/p/w500/7MhXiTmTl16LwXNPbWCmqxj7UxH.jpg', '2023-12-14', 'Emma has a wonderful husband and two kids in the suburbs of New Jersey – she also has a secret life as an assassin for hire – a secret that her husband David discovers when the couple decide to spice up their marriage with a little role play.'),
    ('Poor Things', 'Poor Things', 'https://image.tmdb.org/t/p/w500/kCGlIMHnOm8JPXq3rXM6c5wMxcT.jpg', '2023-12-07', 'Brought back to life by an unorthodox scientist, a young woman runs off with a debauched lawyer on a whirlwind adventure across the continents. Free from the prejudices of her times, she grows steadfast in her purpose to stand for equality and liberation.'),
    ('How Do You Live?', '君たちはどう生きるか', 'https://image.tmdb.org/t/p/w500/wPjtacigLnTQeHxg3XcXQW2kK6K.jpg', '2023-11-24', 'While the Second World War rages, the teenage Mahito, haunted by his mother''s tragic death, is relocated from Tokyo to the serene rural home of his new stepmother Natsuko, a woman who bears a striking resemblance to his mother. As Mahito and his emotionally distant father, Kenzaburo.'),
    ('Kung Fury II: The Movie', 'Kung Fury II: The Movie', 'https://image.tmdb.org/t/p/w500/lOkE3xEaICW4hFFDfaUE1HZO2Y1.jpg', '2023-12-31', 'A renegade cop returns to his despised martial arts squad to retrieve a crossbow stolen by a time-traveling Adolf Hitler. But when the Fuhrer evades capture, Kung Fury must travel through time and assemble the greatest team of Earth''s champions to stop an evil nemesis and save the planet.'),
    ('Dying Light', 'Dying Light', 'https://image.tmdb.org/t/p/w500/q2d2yURUdxkg2G65Wguc9kPm5ao.jpg', '2023-12-31', 'When a group of friends makes a fateful mistake, their entire world is changed overnight. They must now survive the horrors that arise at night, all while trying to find a way to escape their gloomy fate.'),
    ('The Sea Beast', 'The Sea Beast', 'https://image.tmdb.org/t/p/w500/bNrEPbEPrSODiXebjzm2g14lbU.jpg', '2023-12-02', 'The film tells the untold story of young King Neptune. When an unexpected treacherous event gives him the power of the sea, he must learn to control his new powers with the help of a mythical trident.'),
    ('Jurassic World: Dominion', 'Jurassic World: Dominion', 'https://image.tmdb.org/t/p/w500/lyJyX3qLpU2VsDVoc7VV6dC9LwV.jpg', '2022-06-09', 'The saga of the dinosaur park is reimagined in a new story set 65 million years ago, in the age of dinosaurs. With the clock ticking toward the extinction-level asteroid impact, a team of scientists will stop at nothing to ensure the survival of the dinosaurs on Earth.'),
    ('Legacy of Lies 2', 'Legacy of Lies 2', 'https://image.tmdb.org/t/p/w500/mEr4ZZWSsRrC1d3LrfZf6z3KBYV.jpg', '2023-12-31', 'An ex-MI6 agent is thrown back into the world of espionage and high stakes to uncover the shocking truth about operations conducted by unknown secret services.');
    
INSERT INTO users (firstname, lastname, email, password) VALUES 
    ('Alice', 'Johnson', 'alice.johnson@example.com', '$2a$10$pnl.yPeMffoDM8IUpQs/keeSHyilTFaOsAdgCaP6me0Yhx5LmcTri'), -- password: password123
    ('Bob', 'Smith', 'bob.smith@example.com', '$2a$10$fZEwmWm.W/6fpnjHS3pKy.TwxeQF/.2SPTgpu/HsaHkscxILzbOLG'); -- password: password456

INSERT INTO favorites (user_id, movie_id) VALUES 
    (1, 1),
    (1, 2),
    (2, 3),
    (2, 4);

INSERT INTO ratings (user_id, movie_id, score) VALUES 
    (1, 1, 5),
    (1, 2, 4),
    (2, 3, 3),
    (2, 4, 5);
    
INSERT INTO user_rol (user_id, roles_user) VALUES 
    (1, 'ROLE_USER'),
    (2, 'ROLE_ADMIN');
