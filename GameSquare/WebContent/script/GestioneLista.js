/**
 * 
 */
var score  = $('#score').text();
var usrScore = $('#usrScore').text();
//funzione jQuery per l'aggiunta di un gioco a una determinata lista, sulla rispettiva pagina
$("#addButton").on("click", function addToList() {
	var category_value = $('#category').val();
	var user_category = $('#userCategory').text();
    alert(username+" ha aggiunto alla lista di categoria "+category_value+" il gioco id "+game_id+" con un punteggio di "+score+" da un punteggio di "+usrScore);
    //alert(category_value + " " + user_category);
    if(category_value === user_category){
    //alert("categorie uguali");
    
  	Swal.fire({ //SECONDO POPUP
		  			  title: 'Categoria gia selezionata!',
		  			  timer: 1000,
		  			  type: 'warning',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="Game?action=gioco&id="+game_id} , 1000);
    }
    else if(user_category === " "){
      $.ajax({ //INVOCAZIONE AJAX
		  	type: "GET",
		    url: "AddToList",
		    data: {"username" : username, "game_id": game_id, "category_value": category_value, "score": score, "usrScore": usrScore},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Aggiunto alla lista!',
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
    else {
      $.ajax({ //INVOCAZIONE AJAX
		  	type: "GET",
		    url: "ChangeCateg",
		    data: {"username" : username, "game_id": game_id, "category_value": category_value, "score": score, "usrScore": usrScore},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Categoria aggiornta!',
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

//funzione jQuery per la rimozione di un determinato gioco dalla lista, sulla rispettiva pagina
    $('#deleteFromList').on("click", function deleteFromList() {
    	alert(username + " vuole togliere dalla lista " +game_id );
    				$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "DeleteFromList",
					    data: {"username" : username, "game_id": game_id},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Gioco rimosso dalla lista',
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
