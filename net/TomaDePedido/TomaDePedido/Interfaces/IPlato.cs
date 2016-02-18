namespace TomaDePedido.Interfaces
{
    public interface IPlato
    {
        /// <summary>
        /// Codigo del Plato
        /// </summary>
        int Codigo { get; }

        /// <summary>
        /// Nombre del Plato
        /// </summary>
        string Nombre { get; }

        /// <summary>
        /// Precio del Plato
        /// </summary>
        double Precio { get; }

        double ObtenerPrecio();
    }
}
