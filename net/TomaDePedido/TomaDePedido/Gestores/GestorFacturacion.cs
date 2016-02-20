namespace TomaDePedido.Gestores
{
    using System;
    using System.Collections.Generic;
    using TomaDePedido.Interfaces;

    /// <summary>
    /// Gestiona la comunicación con el módulo de Facturacíon
    /// </summary>
    public class GestorFacturacion : IGestorFacturacion
    {
        public void OcuparMesa(int codigo)
        {
            
        }

        public void LiberarMesa(int codigo)
        {
            
        }

        public void EnviarPedido(IPedido pedido)
        {
            throw new NotImplementedException();
        }

        public int ObtenerEstadoMesa(int codigo)
        {
            throw new NotImplementedException();
        }

        public List<IMesa> ObtenerMesas()
        {
            throw new NotImplementedException();
        }

        /// <summary>
        /// Obtiene los pedidos a partir de un codigo de mesa
        /// </summary>
        /// <param name="codigo">Codigo de Mesa</param>
        /// <returns></returns>
        public List<IPedido> ObtenerPedidos(int codigo)
        {
            throw new NotImplementedException();
        }

        public double ObtenerSaldoMesa(int codigo)
        {
            throw new NotImplementedException();
        }
    }
}
