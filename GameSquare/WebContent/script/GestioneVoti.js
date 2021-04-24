/**
 * 
 */

//funzione jQuery per l'aggiunta di un voto a un determinato gioco, sulla rispettiva pagina
$("#voteButton").on("click", function vote() {
	var vote_value = $('#vote').val();
    if(vote_value === null)
	{
		Swal.fire({ 
			title: 'Inserire un voto valido',
			type: 'warning',
			  confirmButtonColor: '#3085d6',
			  confirmButtonText: 'OK',
			width: '400px',
			})
		setTimeout(function(){location.href="pagina-gioco.jsp"} , 135000);
		return false;
	} 
    else
	{
    	//alert(username + " ha votato " +game_id+" con "+vote_value );
    	$.ajax({ //INVOCAZIONE AJAX
		  	type: "POST",
		    url: "AddVote",
		    data: {"username" : username, "game_id": game_id, "vote_value": vote_value},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Valutazione aggiunta!',
		  			  timer: 1000,
		  			  type: 'success',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="Game?action=gioco&id="+game_id} , 1000);
			  }
		})
    }
});



//funzione jQuery per la rimozione di un voto per un determinato gioco, sulla rispettiva pagina
    $('#deleteVote').on("click", function deleteVote() {
    	//alert(username + " vuole togliere il voto a " +game_id );
    				$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "DeleteVote",
					    data: {"username" : username, "game_id": game_id},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Voto Eliminato',
					  			  timer: 1000,
					  			  type: 'success',
					  			  showCancelButton: false,
					  			  showConfirmButton: false,
					  			  width: '400px',
					  			})
					  		setTimeout(function(){location.href="Game?action=gioco&id="+game_id} , 1000);
						  }
					})
			});
  
