PGDMP     -    3            
    z            SSDROLEBASEDAUTH    13.4    13.4     ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    59075    SSDROLEBASEDAUTH    DATABASE     v   CREATE DATABASE "SSDROLEBASEDAUTH" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
 "   DROP DATABASE "SSDROLEBASEDAUTH";
                postgres    false            ?            1259    59076    tbl_role    TABLE     s   CREATE TABLE public.tbl_role (
    role character varying(255) NOT NULL,
    description character varying(255)
);
    DROP TABLE public.tbl_role;
       public         heap    postgres    false            ?            1259    59086    tbl_user    TABLE     ?  CREATE TABLE public.tbl_user (
    id bigint NOT NULL,
    email character varying(255) NOT NULL,
    first_name character varying(30) NOT NULL,
    last_name character varying(30) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255),
    created_at timestamp without time zone,
    created_by character varying(255),
    updated_at timestamp without time zone,
    updated_by character varying(255)
);
    DROP TABLE public.tbl_user;
       public         heap    postgres    false            ?            1259    59084    tbl_user_id_seq    SEQUENCE     x   CREATE SEQUENCE public.tbl_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.tbl_user_id_seq;
       public          postgres    false    202            ?           0    0    tbl_user_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.tbl_user_id_seq OWNED BY public.tbl_user.id;
          public          postgres    false    201            ?            1259    59095    tbl_user_role    TABLE     p   CREATE TABLE public.tbl_user_role (
    user_id bigint NOT NULL,
    role_id character varying(255) NOT NULL
);
 !   DROP TABLE public.tbl_user_role;
       public         heap    postgres    false            ,           2604    59089    tbl_user id    DEFAULT     j   ALTER TABLE ONLY public.tbl_user ALTER COLUMN id SET DEFAULT nextval('public.tbl_user_id_seq'::regclass);
 :   ALTER TABLE public.tbl_user ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    201    202    202            ?          0    59076    tbl_role 
   TABLE DATA           5   COPY public.tbl_role (role, description) FROM stdin;
    public          postgres    false    200   f       ?          0    59086    tbl_user 
   TABLE DATA           ?   COPY public.tbl_user (id, email, first_name, last_name, password, username, created_at, created_by, updated_at, updated_by) FROM stdin;
    public          postgres    false    202   ?       ?          0    59095    tbl_user_role 
   TABLE DATA           9   COPY public.tbl_user_role (user_id, role_id) FROM stdin;
    public          postgres    false    203   T       ?           0    0    tbl_user_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.tbl_user_id_seq', 7, true);
          public          postgres    false    201            .           2606    59083    tbl_role tbl_role_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.tbl_role
    ADD CONSTRAINT tbl_role_pkey PRIMARY KEY (role);
 @   ALTER TABLE ONLY public.tbl_role DROP CONSTRAINT tbl_role_pkey;
       public            postgres    false    200            0           2606    59094    tbl_user tbl_user_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT tbl_user_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.tbl_user DROP CONSTRAINT tbl_user_pkey;
       public            postgres    false    202            6           2606    59099     tbl_user_role tbl_user_role_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.tbl_user_role
    ADD CONSTRAINT tbl_user_role_pkey PRIMARY KEY (user_id, role_id);
 J   ALTER TABLE ONLY public.tbl_user_role DROP CONSTRAINT tbl_user_role_pkey;
       public            postgres    false    203    203            2           2606    59103 %   tbl_user uk_k0bty7tbcye41jpxam88q5kj2 
   CONSTRAINT     d   ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT uk_k0bty7tbcye41jpxam88q5kj2 UNIQUE (username);
 O   ALTER TABLE ONLY public.tbl_user DROP CONSTRAINT uk_k0bty7tbcye41jpxam88q5kj2;
       public            postgres    false    202            4           2606    59101 %   tbl_user uk_npn1wf1yu1g5rjohbek375pp1 
   CONSTRAINT     a   ALTER TABLE ONLY public.tbl_user
    ADD CONSTRAINT uk_npn1wf1yu1g5rjohbek375pp1 UNIQUE (email);
 O   ALTER TABLE ONLY public.tbl_user DROP CONSTRAINT uk_npn1wf1yu1g5rjohbek375pp1;
       public            postgres    false    202            7           2606    59104 )   tbl_user_role fk6phlytlf1w3h9vutsu019xor5    FK CONSTRAINT     ?   ALTER TABLE ONLY public.tbl_user_role
    ADD CONSTRAINT fk6phlytlf1w3h9vutsu019xor5 FOREIGN KEY (role_id) REFERENCES public.tbl_role(role);
 S   ALTER TABLE ONLY public.tbl_user_role DROP CONSTRAINT fk6phlytlf1w3h9vutsu019xor5;
       public          postgres    false    2862    203    200            8           2606    59109 )   tbl_user_role fkggc6wjqokl2vlw89y22a1j2oh    FK CONSTRAINT     ?   ALTER TABLE ONLY public.tbl_user_role
    ADD CONSTRAINT fkggc6wjqokl2vlw89y22a1j2oh FOREIGN KEY (user_id) REFERENCES public.tbl_user(id);
 S   ALTER TABLE ONLY public.tbl_user_role DROP CONSTRAINT fkggc6wjqokl2vlw89y22a1j2oh;
       public          postgres    false    2864    203    202            ?   9   x?st????LL???S(-N-??u?stw??M?KLO-??E??3?!?=... /?      ?   ?   x???1?0 й=?CWj?L*?8I?qi?j?R???wqw~???o}-{?TR????lr???A?2?A?K??qn?I??????Պ?Z%ˡ?????f[?ur??"u??Bg? ????,?!??fa??????'?1?n?2?      ?      x?3?tt????????? ?V     