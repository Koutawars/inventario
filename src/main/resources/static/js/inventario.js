var editando;
$(document).ready(function(){
	$('.modal').modal();
	$.ajax({
		method: "GET",
		url: "/producto",
		datatype: "JSON",
		success: function(data, status, jqXHR ){
			data.forEach(function(i){
				agregarTabla(i.id, i.nombre, i.precio, i.cantidad);
			});
		},
		error: function(jqXHR , status, e){
			M.toast({html: 'Error al traer producto... status: '+jqXHR.status});
			console.log(jqXHR);
		}
	});
	
	  $("#search").on("keyup", function() {
	    var value = $(this).val().toLowerCase();
	    $("#tablaProductos tr").filter(function() {
	      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
	    });
	  });
	 $('#agregar').click(function(e){
		  e.preventDefault();
		  let nombre = $('#nombre').val();
		  let precio = parseInt($('#precio').val());
		  let cantidad = parseInt($('#cantidad').val());
		  if(nombre != '' && precio != '' && cantidad != ''){
			  M.toast({html: 'Agregando...'});
			  // ajax request
			  
			  let producto = {
						nombre,
						precio,
						cantidad
					};
			  
				$.ajax({
					method: "POST",
					url: "producto/add",
					datatype: "JSON",
					contentType: "application/json",
					data: JSON.stringify(producto),
					success: function(data, status, jqXHR ){
						M.toast({html: '¡Inscrito!'});
						agregarTabla(data.id, data.nombre, data.precio, data.cantidad);
					},
					error: function(jqXHR , status, e){
						M.toast({html: 'Error a crear un producto, status: '+jqXHR.status});
						console.log(jqXHR);
					}
				});
		  }
		  
	  });
	  $('#editar').click(function(){
		  let nombre = $('#nombreEdit').val();
		  let precio = $('#precioEdit').val();
		  let cantidad = $('#cantidadEdit').val();
		  if(nombre != '' && precio != '' && cantidad != ''){
			  // Ajax request actualizar
			  M.toast({html: 'Actualizando...'});
			  let producto = {
					  	id:editando,
						nombre,
						precio,
						cantidad
					};
			  
				$.ajax({
					method: "PUT",
					url: "producto/update",
					datatype: "JSON",
					contentType: "application/json",
					data: JSON.stringify(producto),
					success: function(data, status, jqXHR ){
						M.toast({html: '¡Actualizado!'});
						editarTabla(data.id, data.nombre, data.precio, data.cantidad);
					},
					error: function(jqXHR , status, e){
						M.toast({html: 'Error a crear un producto status: '+jqXHR.status});
						console.log(jqXHR);
					}
				});
		  }
	  });
});

function editarTabla(id, nombre, precio, cantidad){
	let markup = `<td>${nombre}</td><td>${precio}</td><td>${cantidad}</td><td><i id="btn${id}" class="small material-icons modal-trigger btn-editar" href="#modal1">edit</i> <i id="del${id}" class="del red-text small material-icons">delete</i></td>`;
	$("tr[name='"+ id +"']").html(markup);
}

function agregarTabla(id, nombre, precio, cantidad){
	let markup = `<tr name="${id}"><td>${nombre}</td><td>${precio}</td><td>${cantidad}</td><td><i id="btn${id}" class="small material-icons modal-trigger btn-editar" href="#modal1">edit</i> <i id="del${id}" class="del red-text small material-icons">delete</i></td></tr>`;
	$('#tablaProductos tbody').append(markup);
	
	$("tr[name='"+ id +"']").on('click', '#btn'+id, function() {
		editando = id;
		let tds = $("tr[name='"+ id +"'] > td");
		$('#nombreEdit').val(tds[0].innerHTML);
		$('#precioEdit').val(tds[1].innerHTML);
		$('#cantidadEdit').val(tds[2].innerHTML);
		M.updateTextFields();
	});
	
	$("tr[name='"+ id +"']").on('click', '#del'+id, function() {
		// AJAX Request remove
		M.toast({html: 'Eliminando...'});
		$.ajax({
			method: "DELETE",
			url: "producto/delete",
			data: JSON.stringify(id),
			contentType: "application/json",
			datatype: "JSON",
			success: function(data, status, jqXHR ){
				$("tr[name='"+ id +"']").remove();
				M.toast({html: '¡Eliminado!'});
			},
			error: function(jqXHR , status, e){
				M.toast({html: 'Error al eliminar status: '+jqXHR.status});
				console.log(jqXHR);
			}
		});
	});
}