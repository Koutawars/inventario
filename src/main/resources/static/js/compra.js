var carro = [], listaTemp = [], cants = [], totalFinal = 0;

$(document).ready(function(){
	calcularTotal();
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
					M.toast({html: 'Error al buscar status: '+jqXHR.status});
					console.log(jqXHR);
				}
			});
		}else{
			$('#tablaBuscar tbody').text("");
		}
	});
	
	$("#comprar").click(function(e){
		if(carro.length == 0){
			  M.toast({html: 'Carro de compra vacío.'});
		}else if($("#cliente").val() == ""){
			  M.toast({html: 'Nombre de cliente.'});
		}else{
			let cliente = $("#cliente").val();
			let fecha = new Date().toJSON().slice(0,10).replace(/-/g,'/');
			let total = totalFinal;
			let compra = {
					fecha,
					cliente,
					total,
					detalle:null
			};
			
			$.ajax({
				method: "POST",
				url: "comprar/add",
				data: JSON.stringify(compra),
				contentType: "application/json",
				datatype: "JSON",
				success: function(compra){
					carro.forEach(async function(producto){
						let detalle = {
							compra,
							producto,
							cantidad: cants[producto.id]
						};
						await new Promise(resolve => {
							$.ajax({
								method: "POST",
								url: "detalle/add",
								data: JSON.stringify(detalle),
								contentType: "application/json",
								datatype: "JSON"
							}).done(function(data){
								resolve();
							});
						});
					});
					setTimeout(function(){ window.location.href = "/factura/"+compra.id; }, 250);
					
				},
				error: function( jqXHR, textStatus, errorThrown ){
					console.log(jqXHR);
				}
			});
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
		cants[id] = cantidad;
		$("tr[name='"+ id +"']").remove();
		carro.push(producto);
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
			cants.splice(id, 1);
			$( "#search" ).trigger( "keyup" );
			calcularTotal();
		});
		calcularTotal();
	});
}

function calcularTotal(){
	let total = 0, final = [];
	carro.forEach(function(e){
		if(e)total += cants[e.id] * e.precio;
	});
	let texto = "" + total;
	let cont = 1;
	for(let i = texto.length - 1; i >= 0; i--){
		if(cont % 3 == 0){
			final[i] =  "." + texto[i];
		}else{
			final[i] = texto[i];
		}
		cont++;
	}
	texto = "";
	for(let i = 0; i < final.length; i ++){
		if(i == 0 && final[i][1])texto += final[i][1];
		else texto += final[i];
	}
	
	$("#total").text("Total $" + texto);
	totalFinal = total;
}