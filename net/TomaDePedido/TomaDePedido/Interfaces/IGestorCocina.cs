namespace TomaDePedido.Interfaces
{
    public interface IGestorCocina
    {
        /// <summary>
        /// Le envia un Detalle de Plato a Cocina para que lo prepare
        /// </summary>
        /// <param name="detalle">Detalle de Plato</param>
        void PedirPlato(IDetallePlato detalle);

        /// <summary>
        /// Le envia el código de Detalle de un Plato para que se cancele y no se prepare
        /// </summary>
        /// <param name="codigoDetalle">Codigo de Detalle</param>
        void CancelarPlato(int codigoDetalle);
    }
}