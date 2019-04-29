var carro = [];
var listaTemp = [];
$(document).ready(function(){
	$("#search").on("keyup", function() {
		let text = $(this).val();
		if(text != ""){
			$.ajax({
				method: "POST",
				url: "producto/buscar",
				data: text,
				contentType: "application/json",
				datatype: "JSON",
				success: function(data, status, jqXHR ){
					if(listaTemp != data){
						listaTemp = data;
						$('#tablaBuscar tbody').text("");
						let enc = false;
						for(let i = 0; i < data.length; i++){
							enc = false;
							carro.forEach(function(e){
								if(e.id == data[i].id) enc = true;
							});
							if(enc) continue;
							if(i < 3){
								agregarTablaBuscar(data[i]);
							}else{
								break;
							}
						}
					}
				},
				error: function(jqXHR , status, e){
					M.toast({html: 'Error al eliminar status: '+jqXHR.status});
					console.log(jqXHR);
				}
			});
		}else{
			$('#tablaBuscar tbody').text("");
		}
	});
	
});

function agregarTablaBuscar(producto){
	let id = producto.id, nombre = producto.nombre, precio = producto.precio;
	let markup = `
	<tr name="${id}">
		<td>${nombre}</td>
		<td>${precio}</td>
		<td>
     		<input value="1" class="col s6" placeholder="Cantidad" id="cant${id}" type="number" class="validate">
		</td>
		<td><i id="add${id}" class="small material-icons modal-trigger btn-add">add</i></td>
	</tr>`;
	$('#tablaBuscar tbody').append(markup);
	
	$("tr[name='" + id + "']").on('click', '#add'+id, function() {
		var cantidad = $('#cant' + id).val();
		$("tr[name='"+ id +"']").remove();
		carro.push(producto);
		console.log("se agrega \n");
		console.log({producto});
		let markup = `
			<tr name="${id}">
				<td>${nombre}</td>
				<td>${precio}</td>
				<td>${cantidad}</td>
				<td><i id="del${id}" class="small material-icons modal-trigger btn-del">delete</i></td>
			</tr>`;
		$('#tablaDetalles tbody').append(markup);
		
		$("tr[name='"+ id +"']").on('click', '#del'+id, function() {
			//remover
			$("tr[name='"+ id +"']").remove();
			carro.splice(carro.indexOf(producto), 1);
			$( "#search" ).trigger( "keyup" );
		});
	});
}