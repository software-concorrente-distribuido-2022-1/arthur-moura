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

            string? n1;
            string? n2;
            string? n3; ;

            try
            {
                NetworkStream stream = new(SocketCliente!);
                saida = new StreamWriter(stream, leaveOpen: true);
                entrada = new StreamReader(stream);

                n1 = entrada.ReadLine();
                n2 = entrada.ReadLine();
                n3 = entrada.ReadLine();

                double nota1 = Double.Parse(n1!);
                double nota2 = Double.Parse(n2!);
                double nota3 = Double.Parse(n3!);

                double M = (nota1 + nota2) / 2

                if (M >= 7)
                    saida.WriteLine("Aluno aprovado.");

                else if (M > 3 && M < 7)
                {
                    if ((M + nota3) / 2 >= 5)
                        saida.WriteLine("Aluno aprovado.");
                    else
                        saida.WriteLine("Aluno reprovado");
                }

                else
                    saida.WriteLine("Aluno reprovado.");

                //else
                //{
                //    erroRqt = true;
                //    msgErro = MsgBadRqt;
                //    saida.WriteLine("Erro ...!");
                //}

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
