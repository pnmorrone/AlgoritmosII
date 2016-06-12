select artist.name_artist as "name of artist",
		title.name_title as "name of title"
	from  artist inner join title_artist on title_artist.id_artist = artist.id_artist
	inner join title on title_artist.id_title=title.id_title;
	
	
select *
	from  title inner join title_filter on title_filter.id_title = title.id_title
	inner join filter on title_filter.id_filter=filter.id_filter;
	
select * from filter inner join label on filter.id_filter = label.id_filter;	
	
select * from label;



select artist.name_artist as "name of artist",
		title.name_title as "name of title",
		filter.name_filter as "name of filter",
		filter.id_filter as "id of filter"
	from  artist 
	inner join title_artist on title_artist.id_artist = artist.id_artist
	inner join title on title_artist.id_title=title.id_title
	inner join title_filter on title.id_title = title_filter.id_title
	inner join filter on title_filter.id_filter=filter.id_filter
	;
	
		
	/*Devuelve todos los titulos con los correspondientes filters*/
	select 
		title.name_title as "name of title",
		filter.name_filter as "name of filter",
		filter.id_filter as "id of filter"
	from  title 
	inner join title_filter on title.id_title = title_filter.id_title
	inner join filter on title_filter.id_filter=filter.id_filter
	;
	
	
	/*Devuelve todos los titulos con los correspondientes filters y labels*/
	select 
		title.name_title as "name of title",
		filter.name_filter as "name of filter",
		filter.id_filter as "id of filter",
		label.name_label as "name of label",
		label.id_label as "id of label"
	from  title 
	inner join title_filter on title.id_title = title_filter.id_title
	inner join filter on title_filter.id_filter=filter.id_filter
	inner join label on filter.id_filter = label.id_filter 
	;
	
	
	
	
	select * from title 
		inner join title_filter on title.id_title=title_filter.id_title
		inner join filter on title_filter.id_filter=filter.id_filter
		inner join label on filter.id_filter = label.id_filter
		where label.name_label LIKE 'Jazz';
	
	
