using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Sockets;
using System.Net;
using System.Text;
using System.Threading.Tasks;

namespace Servidor
{
    public class Conexao : BaseThread
    {
        public readonly string MsgBadRqt = "400 Bad Request";

        Socket SocketCliente;

        public Conexao(Socket aSocketCliente)
        {
            SocketCliente = aSocketCliente;
        }


        public override void RunThread()
        {
            bool erroRqt;
            string? msgErro;

            StreamWriter? saida;
            StreamReader? entrada;

            IPAddress endCliente = ((IPEndPoint)SocketCliente?.RemoteEndPoint)?.Address;

            string? altura;
            string? sexo;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                altura = entrada.ReadLine();
                sexo = entrada.ReadLine();

                double alt = Double.Parse(altura!);

                if (sexo!.Equals("masculino"))
                {
                    double pesoI = (72.7 * alt) - 58;
                    saida.WriteLine("Peso ideal: " + pesoI);
                }

                else if (sexo!.Equals("feminino"))
                {
                    double pesoI = (62.1 * alt) - 44.7;
                    saida.WriteLine("Peso ideal: " + pesoI);
                }

                else
                {
                    erroRqt = true;
                    msgErro = MsgBadRqt;
                    saida.WriteLine("Erro ...!");
                }

                SocketCliente!.Close() ;
                saida.Close();
                entrada.Close();
            }
            catch(IOException e)
            {
                Console.WriteLine("Erro E/S " + e);
            }
        }
    }
}
