using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Webapi.Models
{
    public partial class MyDbContext : DbContext
    {
        public MyDbContext()
        {
        }

        public MyDbContext(DbContextOptions<MyDbContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Flour> Flour { get; set; }
        public virtual DbSet<User> User { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseMySql("server=localhost;port=3306;database=flourstorage;uid=root;password=");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Flour>(entity =>
            {
                entity.ToTable("flour");

                entity.HasIndex(e => e.UserUserid)
                    .HasName("user_userid");

                entity.Property(e => e.Flourid)
                    .HasColumnName("flourid")
                    .HasColumnType("int(11)");

                entity.Property(e => e.Flour1)
                    .IsRequired()
                    .HasColumnName("flour")
                    .HasMaxLength(45);

                entity.Property(e => e.UserUserid)
                    .HasColumnName("user_userid")
                    .HasColumnType("int(11)");

                entity.HasOne(d => d.UserUser)
                    .WithMany(p => p.Flour)
                    .HasForeignKey(d => d.UserUserid)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("flour_ibfk_1");
            });

            modelBuilder.Entity<User>(entity =>
            {
                entity.ToTable("user");

                entity.Property(e => e.Userid)
                    .HasColumnName("userid")
                    .HasColumnType("int(11)");

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnName("password")
                    .HasMaxLength(50);

                entity.Property(e => e.Username)
                    .IsRequired()
                    .HasColumnName("username")
                    .HasMaxLength(150);
            });
        }
    }
}
