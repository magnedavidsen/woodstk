create table if not exists artists (id SERIAL, name VARCHAR(254) NOT NULL,img_url VARCHAR(254) NOT NULL);

INSERT INTO artists (name, img_url) VALUES
('$ushi X Kobe', 'http://goevent-images.s3.amazonaws.com/bylarm-2015/8343e983/web/xl_artist_68_20150112110404_922cc1da.jpg'),
('Alo Wala', 'http://goevent-images.s3.amazonaws.com/bylarm-2015/8343e983/web/xl_artist_387_20150205085821_41ad84d6.jpg'),
('Andre Bratten', 'http://goevent-images.s3.amazonaws.com/bylarm-2015/8343e983/web/xl_artist_201_20150129055417_182bcb12.jpg');



