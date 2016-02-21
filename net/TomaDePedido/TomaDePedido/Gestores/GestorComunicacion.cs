//-----------------------------------------------------------------------
// <copyright file="GestorComunicacion.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using System;
    using System.Net.Http;
    using System.Net.Http.Headers;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicacion via Web.Api
    /// </summary>
    public class GestorComunicacion : IGestorComunicacion
    {
        /// <summary>
        /// Dirección del servicio webApi
        /// </summary>
        private string uri = "http://localhost/yourwebapi";
        
        /// <summary>
        /// Realiza la comunicación con el Servicio de Facturación para obtener el estado de una mesa a partir de su codigo
        /// </summary>
        /// <param name="codigoMesa">Codigo de mesa</param>
        /// <returns>Un entero representando el estado de una mesa</returns>
        public int ObtenerEstadoMesa(int codigoMesa)
        {
            HttpClient client = new HttpClient();
            client.BaseAddress = new Uri(this.uri);

            client.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));

            HttpResponseMessage response = client.GetAsync("api/Mesa").Result;
            if (response.IsSuccessStatusCode)
            {
                var estado = response.Content.ReadAsStringAsync().Result;
                return int.Parse(estado);
            }
            else
            {
                throw new Exception("Error de comunicación con Facturación");
            }
        }
    }
}
