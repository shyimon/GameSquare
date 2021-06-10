
/**
 * Questa funzione JQuery, associata al click del button, prende i parametri dal form di richiesta del gioco e 
 * verifica se hanno un formato corretto, invocando l'operazione della servlet in caso affermativo.
 */
$("#buttonrequest").on('click', function validate(){	
	var gamereq_title  = $('#gamereq_title').val();
	var gamereq_source = $('#gamereq_source').val();
	var publisher  = $('#publisher').val();
	var game_genre = $('#game_genre').val();
	var game_year = $('#game_year').val();
	var username = $('#username').val();

	var RGEXsource = /^(http:\/\/www\.|https:\/\/www\.|http:\/\/|https:\/\/)?[a-z0-9]+([\-\.]{1}[a-z0-9]+)*\.[a-z]{2,5}(:[0-9]{1,5})?(\/.*)?$/gm;
	var sourceRES = RGEXsource.test(gamereq_source);
	

	
	if(gamereq_title === "")
	{
		Swal.fire({ 
			title: 'Inserire il titolo del gioco',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="nuova-richiesta-gioco.jsp"} , 135000);
		return false;
	} 
	else if(publisher === "")
	{
		Swal.fire({ 
			title: 'Inserire il nome del publisher',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="nuova-richiesta-gioco.jsp"} , 135000);
		return false;
	}
	else if(sourceRES == false)
	{
		Swal.fire({ 
			title: 'Inserire una fonte valida',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="nuova-richiesta-gioco.jsp"} , 135000);
		return false;
	}
	{
		$.ajax({ 
		type: "POST",
		url: "CreateGameReq",
		data: {"gamereq_title": gamereq_title, "gamereq_source": gamereq_source,"username":username, "publisher": publisher,"game_genre":game_genre, "game_year":game_year},
		success: function(results){
			Swal.fire({ //SECONDO POPUP
				title: 'Richiesta inviata!',
				text: 'Essa sarà valutata da un gestore catalogo',
				timer: 40000,
				type: 'success',
				showCancelButton: false,
				showConfirmButton: false,
				width: '400px',
				})
			setTimeout(function(){location.href="/GameSquare/index.jsp"} , 2000);
			}    
		})
	}
	
	
	
});