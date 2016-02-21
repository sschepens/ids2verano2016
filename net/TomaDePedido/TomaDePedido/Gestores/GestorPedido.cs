//-----------------------------------------------------------------------
// <copyright file="GestorPedido.cs" company="CAECE ENTERPRAISSSSS">
//     Copyright (c) Caece Enterpraisssss. All rights reserved.
// </copyright>
//-----------------------------------------------------------------------

namespace TomaDePedido.Gestores
{
    using TomaDePedido.Interfaces;
    using TomaDePedido.Enums;
    using System.Collections.Generic;

    /// <summary>
    /// Gestiona la comunicación entre la interfaz de pedidos y los modulos externos de los cuales necesita información
    /// </summary>
    public class GestorPedido : IGestorPedido
    {
        private IGestorCocina Cocina;
        private IGestorStock Stock;
        private IGestorFacturacion Facturacion;
        private IGestorComunicacion Comunicacion;

        public double ObtenerSaldoMesa(int codigo)
        {
            return Facturacion.ObtenerSaldoMesa(codigo);
        }

        public GestorPedido(IGestorCocina cocina, IGestorFacturacion facturacion, IGestorStock stock)
        {
            this.Cocina = cocina;
            this.Stock = stock;
            this.Facturacion = facturacion;
        }

        public GestorPedido()
        {
            this.Comunicacion = new GestorComunicacion();
            this.Cocina = new GestorCocina(this.Comunicacion);
            this.Stock = new GestorStock(this.Comunicacion);
            this.Facturacion = new GestorFacturacion(this.Comunicacion);
        }
        
        public void EnviarPedido(IPedido pedido)
        {
            this.Facturacion.EnviarPedido(pedido);
        }

        public void PedirCerveza(IDetalleCerveza detalle)
        {

        }

        #region "Mesas"
        public void OcuparMesa(int codigoMesa)
        {
            this.Facturacion.OcuparMesa(codigoMesa);
        }

        public void LiberarMesa(int codigoMesa)
        {
            this.Facturacion.LiberarMesa(codigoMesa);
        }

        public Enums.EstadoMesa ObtenerEstadoMesa(int codigoMesa)
        {
            var estado = this.Facturacion.ObtenerEstadoMesa(codigoMesa);
            return (Enums.EstadoMesa)estado;
        }

        public List<IMesa> ObtenerMesas()
        {
            return this.Facturacion.ObtenerMesas();
        }
        #endregion

        public List<IPedido> ObtenerPedidos(int codigo)
        {
            return this.Facturacion.ObtenerPedidos(codigo);
        }

        public void Alertar(int codigo)
        {

        }
    }
}
