var db;
var timer = null;
//var xmlHttp;
var DM_pk = "_pk";
					JStORM.connections = 
					{
						"default":
							{
								PROVIDER:"Gears",
								DIALECT:"SQLite",
								PATH:"test_simple",
								HOST:"database-Quan_Ly_Y_Te"
							}
					};
					
					PhieuNhapKho = new JStORM.Model({
					name: "PHIEU_NHAP_KHO",
							fields: {
								PHIEU_NHAP_KHO_MAPHU: new JStORM.Field({ type: "Float" }),
								PHIEU_NHAP_KHO_MAPHU1: new JStORM.Field({ type: "Float" }),
								SANSANG_CAPNHAT: new JStORM.Field({ type: "Integer" }),
								PHIEUNHAPKHO_MA: new JStORM.Field({ type: "String", maxLength: 15}),
								DTDMLOAI_MA: new JStORM.Field({ type: "String", maxLength: 2}),
								PHIEUNHAPKHO_SOHOADON: new JStORM.Field({ type: "String", maxLength: 12}),
								PHIEUNHAPKHO_NGAYHOADON: new JStORM.Field({ type: "String", maxLength: 10}),
								PHIEUNHAPKHO_CHUNGTU: new JStORM.Field({ type: "String", maxLength: 50}),
								DTDMNGUON_MA: new JStORM.Field({ type: "String", maxLength: 2}),
								DMKINHPHI_MASO: new JStORM.Field({ type: "String", maxLength: 3}),
								DTDMNOIBAN_MA: new JStORM.Field({ type: "String", maxLength: 10}),
								DTDMNHANVIEN_TIEPLIEU: new JStORM.Field({ type: "String", maxLength: 8}),
								PHIEUNHAPKHO_MUCTHUE: new JStORM.Field({ type: "Float"}),
								PHIEUNHAPKHO_THUE: new JStORM.Field({ type: "Float"}),
								PHIEUNHAPKHO_THANHTIEN: new JStORM.Field({ type: "Float"}),
								PHIEUNHAPKHO_TAIKHOAN: new JStORM.Field({ type: "String", maxLength: 10}),
								PHIEUNHAPKHO_DOIUNG: new JStORM.Field({ type: "String", maxLength: 10}),
								PHIEUNHAPKHO_TRONSO: new JStORM.Field({ type: "String", maxLength: 12}),
								PHIEUNHAPKHO_NGAYTT: new JStORM.Field({ type: "String", maxLength: 10}),
								PHIEUNHAPKHO_CHUNGTUTT: new JStORM.Field({ type: "String", maxLength: 18}),
								DTDMNHANVIEN_TT: new JStORM.Field({ type: "String", maxLength: 8}),
								DMKHO_MASO: new JStORM.Field({ type: "String", maxLength: 3}),
								DTDMNHANVIEN_CN: new JStORM.Field({ type: "String", maxLength: 8}),
								PHIEUNHAPKHO_NGAYGIOCN: new JStORM.Field({ type: "String", maxLength: 10}),
								PHIEUNHAPKHO_STT: new JStORM.Field({ type: "Integer"})
							},
							connection: "default"
					}); 
				//
				//alert(1001);
				CtNhapKho = new JStORM.Model({
					name: "CT_NHAP_KHO",
							fields: {
								CT_NHAP_KHO_MAPHU: new JStORM.Field({ type: "Float" }),
								CT_NHAP_KHO_MAPHU1: new JStORM.Field({ type: "Float" }),
								CTNHAPKHO_MA: new JStORM.Field({ type: "Integer"}),
								PHIEUNHAPKHO_MA: new JStORM.Field({ type: "String", maxLength: 15}),
								CTNHAPKHO_MALK: new JStORM.Field({ type: "String", maxLength: 100}),
								DMTHUOC_MASO: new JStORM.Field({ type: "Integer"}),
								DMQUOCGIA_MASO: new JStORM.Field({ type: "Integer"}),
								CTNHAPKHO_THANGNHAP: new JStORM.Field({ type: "String", maxLength: 2}),
								CTNHAPKHO_NAMNHAP: new JStORM.Field({ type: "String", maxLength: 4}),
								CTNHAPKHO_NGAYHANDUNG: new JStORM.Field({ type: "String", maxLength: 2}),
								CTNHAPKHO_THANGHANDUNG: new JStORM.Field({ type: "String", maxLength: 2}),
								CTNHAPKHO_NAMHANDUNG: new JStORM.Field({ type: "String", maxLength: 4}),
								CTNHAPKHO_DONGIA: new JStORM.Field({ type: "Float"}),
								CTNHAPKHO_DONGIABAN: new JStORM.Field({ type: "Float"}),
								CTNHAPKHO_LO: new JStORM.Field({ type: "String", maxLength: 4}),
								CTNHAPKHO_SODANGKY: new JStORM.Field({ type: "String", maxLength: 10}),
								CTNHAPKHO_THUTU: new JStORM.Field({ type: "Integer", maxLength: 10}),
								CTNHAPKHO_SOLUONG: new JStORM.Field({ type: "Float"}),
								CTNHAPKHO_QUYCACH: new JStORM.Field({ type: "Integer"}),
								TONKHO_MA: new JStORM.Field({ type: "Integer"}),
								CTNHAPKHO_NGAYGIOCN: new JStORM.Field({ type: "String", maxLength: 10}),
								DTDMHANGSX_MA: new JStORM.Field({ type: "String", maxLength: 3}),
								PHIEUNHAPKHO_MAPHU: new JStORM.Field({ type: "Float"})
							},
							connection: "default"
					}); 
				//
					
					// khai bao bang DT_DM_KCB_BHYT
					DtDmKcbBhyt = new JStORM.Model({
					name: "DT_DM_KCB_BHYT",
							fields: {

								DT_DM_KCB_BHYT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_KCB_BHYT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});	
					// khai bao bang DT_DM_TIEN_KHAM
					DtDmTienKham = new JStORM.Model({
					name: "DT_DM_TIEN_KHAM",
							fields: {

								DT_DM_TIEN_KHAM_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_TIEN_KHAM_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								DonGia: new JStORM.Field({ type: "Float" }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});	

				// khai bao bang DT_DM_TINH_BHYT
				DtDmTinhBhyt = new JStORM.Model({
					name: "DT_DM_TINH_BHYT",
							fields: {

								DT_DM_TINH_BHYT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_TINH_BHYT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});	
				// khai bao bang DT_DM_KHOI_BHYT
				DtDmKhoiBhyt = new JStORM.Model({
					name: "DT_DM_KHOI_BHYT",
							fields: {

								DT_DM_KHOI_BHYT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_KHOI_BHYT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});	
         
			// khai bao bang DT_DM_BAN_KHAM
			DtDmBanKham = new JStORM.Model({
					name: "DT_DM_BAN_KHAM",
							fields: {

								DT_DM_BAN_KHAM_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_BAN_KHAM_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});    
					// khai bao bang DM_PL_TAI_NAN
					DmPlTaiNan = new JStORM.Model({
					name: "DM_PL_TAI_NAN",
							fields: {

								DM_PL_TAI_NAN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_PL_TAI_NAN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
					// khai bao bang DT_DM_GIA_PHONG
					DtDmGiaPhong = new JStORM.Model({
					name: "DT_DM_GIA_PHONG",
							fields: {

								DT_DM_GIA_PHONG_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_GIA_PHONG_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
					
				// khai bao bang DT_DM_DOI_TUONG
				DtDmDoiTuong = new JStORM.Model({
					name: "DT_DM_DOI_TUONG",
							fields: {

								DT_DM_DOI_TUONG_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_DOI_TUONG_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								TyLeMien: new JStORM.Field({ type: "String", maxLength: 10 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				//alert(111001);	
					
				// khai bao bang DM_BENH_ICD
				DmBenhIcd = new JStORM.Model({
					name: "DM_BENH_ICD",
							fields: {

								DM_BENH_ICD_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_BENH_ICD_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});  
				// khai bao bang DM_DM_CLS
				DtDmCls = new JStORM.Model({
					name: "DT_DM_CLS",
							fields: {

								DT_DM_CLS_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_CLS_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				// khai bao bang DT_DM_PHAU_THUAT
				DtDmPhauThuat = new JStORM.Model({
					name: "DT_DM_PHAU_THUAT",
							fields: {

								DT_DM_PHAU_THUAT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_PHAU_THUAT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				// khai bao bang DT_DM_VO_CAM
				 DtDmVoCam = new JStORM.Model({
					name: "DT_DM_VO_CAM",
							fields: {

								DT_DM_VO_CAM_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_VO_CAM_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});    
				// khai bao bang DM_DAN_TOC
				DmDanToc = new JStORM.Model({
					name: "DM_DAN_TOC",
							fields: {

								DM_DAN_TOC_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_DAN_TOC_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});	
				// khai bao bang DM_TINH
				DmTinh = new JStORM.Model({
					name: "DM_TINH",
							fields: {

								DM_TINH_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_TINH_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				// khai bao bang DM_HUYEN
				DmHuyen = new JStORM.Model({
					name: "DM_HUYEN",
							fields: {

								DM_HUYEN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_HUYEN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" }),
								RefMa: new JStORM.Field({ type: "String", maxLength: 15	 })
			
							},
							connection: "default"
					});
				// khai bao bang DM_XA
				DmXa = new JStORM.Model({
					name: "DM_XA",
							fields: {

								DM_XA_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_XA_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" }),
								RefMa: new JStORM.Field({ type: "String", maxLength: 15	 })
			
							},
							connection: "default"
					});
				// khai bao bang DM_NGHE_NGHIEP
				DmNgheNghiep = new JStORM.Model({
					name: "DM_NGHE_NGHIEP",
							fields: {

								DM_NGHE_NGHIEP_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_NGHE_NGHIEP_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				//
					// khai bao bang DM_KHO
					DmKho = new JStORM.Model({
					name: "DM_KHO",
							fields: {

								DM_KHO_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_KHO_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
					// khai bao bang DT_DM_NOI_BAN
					DtDmNoiBan = new JStORM.Model({
					name: "DT_DM_NOI_BAN",
							fields: {

								DT_DM_NOI_BAN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_NOI_BAN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				// khai bao bang DT_DM_NGUON
				DtDmNguon = new JStORM.Model({
					name: "DT_DM_NGUON",
							fields: {

								DT_DM_NGUON_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_NGUON_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				//
				// khai bao bang DM_KINH_PHI
				DmKinhPhi = new JStORM.Model({
					name: "DM_KINH_PHI",
							fields: {

								DM_KINH_PHI_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_KINH_PHI_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				//
				// khai bao bang DT_DM_NHAN_VIEN
				DtDmNhanVien = new JStORM.Model({
					name: "DT_DM_NHAN_VIEN",
							fields: {

								DT_DM_NHAN_VIEN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_NHAN_VIEN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				// khai bao bang DM_THUOC
				DmThuoc = new JStORM.Model({
					name: "DM_THUOC",
							fields: {

								DM_THUOC_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_THUOC_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								DonVi: new JStORM.Field({ type: "String", maxLength: 10 }),
								HamLuong: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai1: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai2: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai3: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai4: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai5: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai6: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai7: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai8: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai9: new JStORM.Field({ type: "String", maxLength: 10 }),
								ploai10: new JStORM.Field({ type: "String", maxLength: 10 }),
								loai: new JStORM.Field({ type: "String", maxLength: 10 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" }),
								RefMa: new JStORM.Field({ type: "String", maxLength: 15	 })
			
							},
							connection: "default"
					});
				// khai bao bang DM_QUOC_GIA
				DmQuocGia = new JStORM.Model({
					name: "DM_QUOC_GIA",
							fields: {

								DM_QUOC_GIA_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_QUOC_GIA_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 

				// khai bao bang DM_KHOA
				DmKhoa = new JStORM.Model({
					name: "DM_KHOA",
							fields: {

								DM_KHOA_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_KHOA_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				// khai bao bang DT_DM_LOAI
				DtDmLoai = new JStORM.Model({
					name: "DT_DM_LOAI",
							fields: {

								DT_DM_LOAI_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_LOAI_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
														
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
				// khai bao bang DT_DM_HANG_SX
				DtDmHangSx = new JStORM.Model({
					name: "DT_DM_HANG_SX",
							fields: {

								DT_DM_HANG_SX_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_HANG_SX_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				// khai bao bang DT_DM_DVT
				DtDmDvt = new JStORM.Model({
					name: "DT_DM_DVT",
							fields: {

								DT_DM_DVT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_DVT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo_pk: new JStORM.Field({ type: "String", maxLength: 15	}),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				// khai bao bang DM_BENH_VIEN
				DmBenhVien = new JStORM.Model({
					name: "DM_BENH_VIEN",
							fields: {

								DM_BENH_VIEN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_BENH_VIEN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
			// khai bao bang DM_PHUONG_TIEN
			DmPhuongTien = new JStORM.Model({
					name: "DM_PHUONG_TIEN",
							fields: {

								DM_PHUONG_TIEN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_PHUONG_TIEN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});   
         	// khai bao bang DM_DIEU_TRI
			DmDieuTri = new JStORM.Model({
					name: "DM_DIEU_TRI",
							fields: {

								DM_DIEU_TRI_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_DIEU_TRI_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
			// khai bao bang DT_DM_KET_QUA
			DtDmKetQua = new JStORM.Model({
					name: "DT_DM_KET_QUA",
							fields: {

								DT_DM_KET_QUA_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_KET_QUA_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
			// khai bao bang DT_DM_HUONG_DT
			DtDmHuongDt = new JStORM.Model({
					name: "DT_DM_HUONG_DT",
							fields: {

								DT_DM_HUONG_DT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_HUONG_DT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
		//khai bao bang DT_DM_NOI_SINH
		DtDmNoiSinh = new JStORM.Model({
					name: "DT_DM_NOI_SINH",
							fields: {

								DT_DM_NOI_SINH_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_NOI_SINH_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
		//khai bao bang DM_LY_DO_CV
		DmLyDoCv = new JStORM.Model({
					name: "DM_LY_DO_CV",
							fields: {

								DM_LY_DO_CV_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_LY_DO_CV_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
		//khai bao bang DM_DIA_DIEM
		DmDiaDiem = new JStORM.Model({
					name: "DM_DIA_DIEM",
							fields: {

								DM_DIA_DIEM_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_DIA_DIEM_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
					
		DmLoaiBaoCaoHSBADangCN = 	new JStORM.Model({
					name: "DM_LOAI_BAO_CAO_HSBA_DANG_CN",
							fields: {

								DM_LOAI_BAO_CAO_HSBA_DANG_CN_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_LOAI_BAO_CAO_HSBA_DANG_CN_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
		DmPhanLoai = new JStORM.Model({
					name: "DM_PHAN_LOAI",
							fields: {

								DM_PHAN_LOAI_MAPHU: new JStORM.Field({ type: "Float"	}),
								DM_PHAN_LOAI_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 4	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 30 }),
								Loai: new JStORM.Field({ type: "String", maxLength: 3	}),	
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					}); 
								
			
      			 //
				DtDmKyThuat = new JStORM.Model({
					name: "DT_DM_KY_THUAT",
							fields: {

								DT_DM_KY_THUAT_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_KY_THUAT_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" }),
								LoaiCLS: new JStORM.Field({ type: "String" , maxLength: 15}),
								LoaiCLSFather: new JStORM.Field({ type: "String" , maxLength: 15}),
								ChuDau: new JStORM.Field({ type: "String" , maxLength: 15}),
								ChuDauFather: new JStORM.Field({ type: "String" , maxLength: 15}),
								PhanBietCLS: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa1: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa2: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa3: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa4: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa5: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa6: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa7: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa8: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa9: new JStORM.Field({ type: "String" , maxLength: 15}),
								Khoa10: new JStORM.Field({ type: "String" , maxLength: 15 })
			
							},
							connection: "default"
					});
					
				//
				DtDmPbCls = new JStORM.Model({
					name: "DT_DM_PB_CLS",
							fields: {

								DT_DM_PB_CLS_MAPHU: new JStORM.Field({ type: "Float"	}),
								DT_DM_PB_CLS_MAPHU1: new JStORM.Field({ type: "Float" }),
								MaSo: new JStORM.Field({ type: "String", maxLength: 15	}),
								Ten: new JStORM.Field({ type: "String", maxLength: 250 }),
								NgayChinhSua: new JStORM.Field({ type: "Float" }),
								DaXoa: new JStORM.Field({ type: "Integer" })
			
							},
							connection: "default"
					});
				//	
					
function LoadCatalogFromServer() {
	
    PhieuNhapKho.createTable();
	CtNhapKho.createTable();

	DmKho.createTable();
    DtDmNoiBan.createTable();
    DtDmNguon.createTable();
	DmKinhPhi.createTable();
	DtDmNhanVien.createTable();
	DmThuoc.createTable();
	DmQuocGia.createTable();
	DmKhoa.createTable();
	DtDmLoai.createTable();
	DtDmHangSx.createTable();
	DtDmDvt.createTable();
	DtDmDoiTuong.createTable();
	
	DmDanToc.createTable();
    DmTinh.createTable();
    DmHuyen.createTable();
    DmXa.createTable();
    DmNgheNghiep.createTable();
    DmBenhVien.createTable();
    DmBenhIcd.createTable();
    DtDmPhauThuat.createTable();
    DtDmVoCam.createTable();
    
    DtDmKcbBhyt.createTable();
    DtDmTienKham.createTable();
    DtDmBanKham.createTable();
    DtDmTinhBhyt.createTable();
    DtDmKhoiBhyt.createTable();
    DmPhuongTien.createTable();
    
    DmPlTaiNan.createTable();
    DmDieuTri.createTable();
    DtDmHuongDt.createTable();
    DtDmNoiSinh.createTable();
    DmLyDoCv.createTable();
    DmLoaiBaoCaoHSBADangCN.createTable();
    DmPhanLoai.createTable();
    DtDmKyThuat.createTable();
    DtDmKetQua.createTable();
    
    DtDmCls.createTable();
	DtDmPbCls.createTable();
	DmDiaDiem.createTable();
	DtDmGiaPhong.createTable();


	getCatalogFromServer_new('DM_XA','GetXaAction', DmXa);

    getCatalogFromServer_new('DM_LOAI_BAO_CAO_HSBA_DANG_CN','GetLoaiBaoCaoBADangCapNhatAction',DmLoaiBaoCaoHSBADangCN);
    
	getCatalogFromServer_new('DM_QUOC_GIA','GetQuocGiaAction',DmQuocGia);
    getCatalogFromServer_new('DT_DM_NOI_BAN','GetNoiBanAction',DtDmNoiBan);
    getCatalogFromServer_new('DM_KINH_PHI','GetKinhPhiAction',DmKinhPhi);
    getCatalogFromServer_new('DT_DM_NHAN_VIEN','GetNhanVienAction',DtDmNhanVien);
    getCatalogFromServer_new('DM_THUOC','GetDanhMucThuocAction',DmThuoc);
    getCatalogFromServer_new('DT_DM_NGUON','GetNguonAction',DtDmNguon);
    getCatalogFromServer_new('DM_KHOA','GetKhoaAction',DmKhoa);
    getCatalogFromServer_new('DM_KHO','GetKhoAction',DmKho);
    getCatalogFromServer_new('DT_DM_LOAI','GetLoaiAction',DtDmLoai);
    getCatalogFromServer_new('DT_DM_HANG_SX','GetHangSanXuatAction',DtDmHangSx);
    getCatalogFromServer_new('DT_DM_DVT','GetDonViTinhAction',DtDmDvt);
    
    getCatalogFromServer_new('DM_DAN_TOC','GetDanTocAction', DmDanToc);
    getCatalogFromServer_new('DM_TINH','GetTinhAction', DmTinh);
    getCatalogFromServer_new('DM_HUYEN','GetHuyenAction', DmHuyen);
    getCatalogFromServer_new('DM_XA','GetXaAction', DmXa);
    
    getCatalogFromServer_new('DM_NGHE_NGHIEP','GetDmNgheAction', DmNgheNghiep);
    getCatalogFromServer_new('DM_BENH_ICD','GetBenhIcdAction', DmBenhIcd);
    getCatalogFromServer_new('DT_DM_PHAU_THUAT','GetDtDmPhauThuatAction', DtDmPhauThuat);
    getCatalogFromServer_new('DT_DM_VO_CAM','GetDtDmVoCamAction', DtDmVoCam);
    getCatalogFromServer_new('DM_DM_CLS','GetDtDmClsAction', DtDmCls);
    getCatalogFromServer_new('DT_DM_DOI_TUONG','GetDoiTuongAction',DtDmDoiTuong);
    
	getCatalogFromServer_new('DT_DM_KCB_BHYT','GetKCBBHYTAction', DtDmKcbBhyt);
   	getCatalogFromServer_new('DT_DM_TIEN_KHAM','GetTienKhamAction', DtDmTienKham);
    getCatalogFromServer_new('DT_DM_BAN_KHAM','GetBanKhamAction', DtDmBanKham);
   	getCatalogFromServer_new('DT_DM_TINH_BHYT','GetDtDmTinhAction', DtDmTinhBhyt);
    getCatalogFromServer_new('DT_DM_KHOI_BHYT','GetDtdmKhoiAction', DtDmKhoiBhyt);
    getCatalogFromServer_new('DM_BENH_VIEN','GetBenhVienAction', DmBenhVien);
    getCatalogFromServer_new('DM_PHUONG_TIEN','GetPhuongTienAction', DmPhuongTien);
    
    getCatalogFromServer_new('DM_PL_TAI_NAN','GetDmTaiNanAction',DmPlTaiNan);
    
    getCatalogFromServer_new('DM_DIEU_TRI','GetDieuTriAction',DmDieuTri);
    getCatalogFromServer_new('DT_DM_HUONG_DT','GetHuongDieuTriAction',DtDmHuongDt);
    getCatalogFromServer_new('DT_DM_NOI_SINH','GetDmNoiSinhAction',DtDmNoiSinh);
    getCatalogFromServer_new('DM_LY_DO_CV','GetDmLyDoCVAction',DmLyDoCv);

    getCatalogFromServer_new('DM_PHAN_LOAI','GetDmPhanLoaiAction',DmPhanLoai);
    getCatalogFromServer_new('DT_DM_KY_THUAT','GetDmKyThuatAction',DtDmKyThuat);
    
    getCatalogFromServer_new('DT_DM_KET_QUA','GetKQDieuTriAction',DtDmKetQua);

 	getCatalogFromServer_new('DT_DM_CLS','GetDtDmClsAction',DtDmCls);
	getCatalogFromServer_new('DT_DM_PB_CLS','GetDtDmPbClsAction',DtDmPbCls);
	
	getCatalogFromServer_new('DM_DIA_DIEM','GetDmDiaDiemAction',DmDiaDiem);
	getCatalogFromServer_new('DT_DM_GIA_PHONG','GetGiaPhongAction',DtDmGiaPhong);

}


function getObject(name) {
	switch (name) {
		case "DT_DM_GIA_PHONG":
			return DtDmGiaPhong;	
		case "DM_QUOC_GIA":
			return DmQuocGia;
		case "DT_DM_NOI_BAN":
			return DtDmNoiBan;
		case "DM_KINH_PHI":
			return DmKinhPhi;
		case "DT_DM_NHAN_VIEN":
			return DtDmNhanVien;
		case "DM_THUOC":
			return DmThuoc;
		case "DT_DM_NGUON":
			return DtDmNguon;
		case "DM_KHO":
			return DmKho;
		case "DT_DM_LOAI":
			return DtDmLoai;
		case "DT_DM_HANG_SX":
			return DtDmHangSx;
		case "DT_DM_DVT":
			return DtDmDvt;
		case "DM_TINH":
			return DmTinh;
		case "DM_HUYEN":
			return DmHuyen;
		case "DM_XA":
			return DmXa;
		case "DM_DAN_TOC":
			return DmDanToc;
		case "DT_DM_BAN_KHAM":
			return DtDmBanKham;
		case "DM_NGHE_NGHIEP":
			return DmNgheNghiep;
		case "DM_BENH_ICD":
			return DmBenhIcd;
		case "DT_DM_DOI_TUONG":
			return DtDmDoiTuong;
		case "DM_PHUONG_TIEN":
			return DmPhuongTien;
		case "DM_PL_TAI_NAN":
			return DmPlTaiNan;
		case "DM_BENH_VIEN":
			return DmBenhVien;
		case "DT_DM_KET_QUA":
			return DtDmKetQua;
		case "DT_DM_HUONG_DT":
			return DtDmHuongDt;
		case "DM_KHOA":
			return DmKhoa;
		case "DM_LY_DO_CV":
			return DmLyDoCv;
		case "DM_DIEU_TRI":
			return DmDieuTri;
		case "DM_PHAN_LOAI":
		    return DmPhanLoai;
		case "DT_DM_NOI_SINH":
		    return DtDmNoiSinh; 
		case "DT_DM_KCB_BHYT":
		    return DtDmKcbBhyt;   
		case "DT_DM_CLS":
		    return DtDmCls;      
		case "DT_DM_PB_CLS":
		    return DtDmPbCls;  
		case "DT_DM_KY_THUAT":
		    return DtDmKyThuat; 
		case "DM_DIA_DIEM":
		    return DmDiaDiem;      
		case "DT_DM_TIEN_KHAM":
		    return DtDmTienKham;
		case "DT_DM_VO_CAM":
		    return DtDmVoCam;  
		case "DT_DM_PHAU_THUAT":
		    return DtDmPhauThuat;   
		case "DT_DM_KHOI_BHYT":
		    return DtDmKhoiBhyt;   
		case "DM_LOAI_BAO_CAO_HSBA_DANG_CN":
		    return DmLoaiBaoCaoHSBADangCN;  
		case "DT_DM_TINH_BHYT":
		    return DtDmTinhBhyt;      
		    
		    
		    
		       		           
		default:
		    
		    
		    alert("error at getObject():" + name);
			return null;
			
			
			
			
	}
	return null;
}

function myOnblurTextboxForPhanLoaiThuoc(textboxId,comboboxId,loai_value){
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var arr2 = comboboxId.split("__");
	var myTableName = "";
	if(arr2.length > 1){
		myTableName = arr2[0];
	} else {
	   myTableName = comboboxId;
	}
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("MaSo like ?", textboxValue ).filter("Loai like ?", loai_value ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		var end = false;
		for (var i = 0; i < objectarr.length; i++) {
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", objectarr[i].Ten: " + objectarr[i].Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo_pk;
			}
			catch(e){}
			return;
		}
	    
	    mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    }
    //alert(2);
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
 	//db.close();     
}
function myOnblurTextbox(textboxId,comboboxId){
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	
	var arr2 = comboboxId.split("__");
	var myTableName = "";
	if(arr2.length > 1){
		myTableName = arr2[0];
	} else {
	   myTableName = comboboxId;
	}
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("MaSo like ?", textboxValue ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo_pk;
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}
	
 	//db.close();     
}


function myOnblurTextboxForDmThuoc(textboxId,comboboxId, loai_value, phanloai_value){
  try{
	var mytextbox = document.getElementById(textboxId);
	//alert(mytextbox);
	var textboxValue = mytextbox.value;
	
	var mycombobox = document.getElementById(prefix_component + comboboxId);
	if (mycombobox == null){
	   mycombobox = document.getElementById( comboboxId);
	}
	//alert("comboboxId1:"+comboboxId);
	var havingData = false;
	if(textboxValue != ""){
		//alert(mytextbox);
		
		if (mycombobox == null || mycombobox == ""){
			mycombobox = document.getElementById( comboboxId);
		}
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var arr2 = myTableName.split(":");
		
		//alert(comboboxIdJSF);
		if(arr2.length > 1){
	       myTableName = arr2[arr2.length  - 1];
		}else{
		   myTableName = arr2[0];
		}
		
		
		var arr2 = myTableName.split(":");
		
		//alert(comboboxIdJSF);
		if(arr2.length > 1){
	       myTableName = arr2[arr2.length  - 1];
		}else{
		   myTableName = arr2[0];
		}
		
		if (loai_value == null || loai_value == ""){
			loai_value = "%";
		}
		if (phanloai_value == null || phanloai_value == ""){
			phanloai_value = "%";
		}
		var dmthuocArray =   DmThuoc.filter("MaSo like ?", textboxValue).filter("loai like ?", loai_value).filter("(ploai1 like ? OR ploai2 like ? OR ploai3 like ? OR ploai4 like ? OR ploai5 like ? OR ploai6 like ? OR ploai7 like ? OR ploai8 like ? OR ploai9 like ? OR ploai10 like ?)", phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value).toArray();
	   // var search = dijit.byId(myCombobox.id);
		//var jsonData = { identifier: "id", items: [], label: "title" };
		//var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		//CatalogStore.newItem({id: "", title: ""});
	    if ( dmthuocArray != null ) {
	    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
	    	 	 mycombobox.value = dmthuocArray[i].Ten;
	    	 	 try{
					document.getElementById(textboxId + DM_pk).value = dmthuocArray[i].MaSo_pk;
				}
				catch(e){}
		        havingData = true;
		        return;
	            
	         }
	    }
		
	    //alert(2);
	    if(dmthuocArray == null){
	    	mytextbox.alt = "";
	    }
	    
	    mycombobox.value = "";   
	    //alert(5);
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
    }else{
        //alert("mycombobox:"+mycombobox);
        //if (mycombobox != null){
       	//  mycombobox.value = "";
        //}
        
        //alert();
    }
    if(havingData == false ){
       mycombobox.value = "";
       try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
    
  }catch(e){
    alert("myOnblurTextboxForDmThuoc():"+e);
  }  
      
}



function myOnblurTextboxForDtDmKyThuat(textboxId,comboboxId,chuDau){
  try{
	mytextbox = document.getElementById(textboxId);
	//alert(mytextbox);
	textboxValue = mytextbox.value;
	var havingData = false;
	if(textboxValue != ""){
		//alert(mytextbox);
		mycombobox = document.getElementById(prefix_component + comboboxId);
		if (mycombobox == null){
			 mycombobox = document.getElementById( comboboxId);
		}
		//alert(mycombobox);
		
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\' and loai like '" + loai_value + "'");
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		
		if (chuDau == null || chuDau == ""){
			chuDau = "%";
		}
		
		var Object = getObject(comboboxId);
		var end = false;
		var objArr = Object.filter("MaSo like ? and (ChuDau like ? or ChuDauFather like ?)", textboxValue, chuDau, chuDau).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
			havingData = true;
			try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo_pk;
			}
			catch(e){
			
			}
		}
		


		    try{
					document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
			if (havingData == true){
	      		return;
	    	}			
			mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";		
		    mytextbox.focus();
		/*
		var strSql = "select * from " + comboboxId + " where MaSo like \'" + textboxValue + "\' and ( ChuDau like '" + chuDau + "' or ChuDauFather like '" + chuDau + "' )";
		//alert(strSql);
		var rs = db.execute(strSql);
		
		
		// alert(11);
	    while (rs.isValidRow()) {
	        //alert(33);
	        mycombobox.value = rs.fieldByName("Ten");
	        //alert(34);
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        //mycombobox.select();
	        //   alert(1);
	        return;
	        rs.next();
	    }
	    //alert(2);
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }
	    //alert(3);
	    rs.close();
	    //  alert(4);
	    mycombobox.value = "";   
	    //alert(5);
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
	    */
    }
  }catch(e){
    alert("myOnblurTextboxForDmThuoc():"+e);
  }  
      
}

function myOnblurTextbox_Huyen_Xa(textboxId,comboboxId,father_id){  //father_id = TINH_MA or father_id = HUYEN_MA 
  try{
    
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){

		mycombobox = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)

		//thanh added
		fathertextbox = document.getElementById(father_id);
		value_father = fathertextbox.value;
		if (value_father == null || value_father == ""){
			mytextbox.value = "";
			//document.getElementById( father_id).focus();
			return;	
		}
		//end thanh added
		
		//alert("value_father:"+value_father);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("MaSo like ? and RefMa = ?", textboxValue, value_father).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
	        //alert("mycombobox.value:" + mycombobox.value);
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo_pk;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
				}
				catch(e){}
		    mytextbox.focus();
		/*
		//alert("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		//var rs = db.execute("select * from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'" + "and RefMa=\'" + value_father + "\'");
	    //alert("mycombobox:" + mycombobox);
	    while (rs.isValidRow()) {
	        mycombobox.value = rs.fieldByName("Ten");
	        //alert("mycombobox.value:" + mycombobox.value);
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        //rs.next();
	    }
	    //alert("rs:" + rs);
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }
	    rs.close();
	    mycombobox.value = "";   
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    mytextbox.focus();
	    */
    }
     
  }catch(e){
    alert("myOnblurTextbox_Huyen_Xa():"+e);
  }    
}


function myOnblurTextbox1(textboxId,comboboxId){
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		
		myCombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var end = false;
		var objArr = Object.filter("MATHANG_MA like ?", textboxValue).toArray();
		for (var i = 0; i < objArr.length; i++) {
			listCatalog1(myCombobox);
			return;
		}
		
		myCombobox.value = "";   
	    	mytextbox.value="";
	   		mytextbox.focus();
		/*
		var rs = db.execute("select * from " + comboboxId + " where MATHANG_MA like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        //myCombobox1.value = rs.fieldByName("Ten");
	        listCatalog1(myCombobox);
	        return;
	        rs.next();
	    }
	    rs.close();
	    myCombobox.value = "";   
	    mytextbox.value="";
	    mytextbox.focus();
	    */
    }
      
}

function listCatalog1(myCombobox){
/*
	try{
	   	rs = db.execute("SELECT * FROM " + myCombobox.id + " WHERE Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	   	
	   }
	   catch(e){}
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myCombobox.id);
	var end = false;
	var objArr = Object.filter("Ten like \'" + myCombobox.getDisplayedValue() + "%\'").toArray();
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].MATHANG_MA, 
							title: objArr[i].MATHANG_MASO + objArr[i].MATHANG_MA + 
									objArr[i].MATHANG_TEN + objArr[i].MATHANG_DONGIA + objArr[i].MATHANG_TON});
	}
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MATHANG_MA"), title: rs.fieldByName("MATHANG_MASO") + rs.fieldByName("MATHANG_MA") + 
		rs.fieldByName("MATHANG_TEN") + rs.fieldByName("MATHANG_DONGIA") + rs.fieldByName("MATHANG_TON")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}


function myOnblurTextboxJSF(textboxId,comboboxId){
  try{
	mytextbox = document.getElementById(textboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1]; 
	
	//alert(comboboxId);
	
	textboxValue = mytextbox.value;	

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	//alert(comboboxIdJSF);
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		    	 myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	
	if(textboxValue != ""){
        //alert(1);
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("MaSo like ?", textboxValue).toArray();
		for (var i = 0; i < objArr.length; i++) {
		    var obj = objArr[i];
			mycombobox.value = obj.Ten;
			mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = obj.MaSo_pk;
			}
			catch(e){}
			return;
		}
		
		//alert(1);
		/*
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        mycombobox.value = rs.fieldByName("Ten");
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        //rs.next();
	    }
	    */
	    mytextbox.alt = "";
	    	mycombobox.value = "";   
		    mytextbox.value = "";
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
				}
				catch(e){}
		    mytextbox.focus();
	    
    }else{
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
    
    }catch(e){
      alert(e + "1");
    }
      
}

function myOnblurTextboxJSF_Huyen_Xa(textboxId,comboboxId,father_id){  //father_id = TINH_MA or father_id = HUYEN_MA 
	
	//alert(1);
	
	mytextbox = document.getElementById(textboxId);
	//alert("mytextbox:"+mytextbox);
	//thanh added
	fathertextbox = document.getElementById(prefix_component + father_id);
	value_father = fathertextbox.value;
	if (value_father == null || value_father == ""){
		mytextbox.value = "";
		//document.getElementById( father_id).focus();
		return;	
	}
	//end thanh added
	
	arr1 = comboboxId.split(":");
	//alert("arr1:"+arr1);
	comboboxIdJSF = arr1[arr1.length - 1]; 
	
	//alert(comboboxId);
	
	textboxValue = mytextbox.value;	

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	//alert(comboboxIdJSF);
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		    	 myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	
	if(textboxValue != ""){

		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("MaSo like \'%" + textboxValue + "\'" + "and RefMa=\'" + value_father + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mycombobox.value = objArr[i].Ten;
			mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo_pk;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.value = "";   
		    mytextbox.value = "";
		    try{
					document.getElementById(textboxId + DM_pk).value = "";
				}
				catch(e){}
		    mytextbox.focus();
		/*
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'%" + textboxValue + "\'" + "and RefMa=\'" + value_father + "\'");
	    while (rs.isValidRow()) {
	        mycombobox.value = rs.fieldByName("Ten");
	        mycombobox.select();
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }
	    rs.close();
	    mycombobox.value = "";   
	    mytextbox.value = "";
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    mytextbox.focus();
	    */
    }
      
}


function myOnblurCombobox(textboxId,comboboxId){
	//alert("myOnblurCombobox");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	//alert("comboboxValue:"+comboboxValue);
	if(comboboxValue != ""){
		//alert("comboboxValue: " + comboboxValue);
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		//alert(myTableName);
		
		
		var obj = getObject(myTableName) ;
		//alert("obj:" + obj);
		mytextbox = document.getElementById(textboxId);
		//alert("comboboxValue:"+comboboxValue);
		var arrayObj =   obj.filter("Ten like ?", comboboxValue).toArray();
		//alert(arrayObj);
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.alt = arrayObj[i].MaSo;
			// alert(arrayObj[i].MaSo);
			//  alert(arrayObj[i].Ten);
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo_pk;
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}

function getTrueNameOnPortal(myname){
   		
   		var arr1 = myname.split(":");
		if(arr1.length > 1){
	       myTableName = arr1[arr1.length - 1];
		}else{
		   myTableName = arr1[0];
		}	
		
		
}
//thanhdo
function myOnblurComboboxForDmThuoc(textboxId,comboboxId, loai_value, phanloai_value){

	//alert("comboboxId:"+comboboxId);
	
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){

		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		mytextbox = document.getElementById(textboxId);
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		
		if (loai_value == null || loai_value == ""){
			loai_value = "%";
		}
		if (phanloai_value == null || phanloai_value == ""){
			phanloai_value = "%";
		}
		
		arr1 = comboboxId.split(":");
		if(arr1.length > 1){
	       myTableName = arr1[arr1.length - 1];
		}else{
		   myTableName = arr1[0];
		}		
	/*
		var strSql = "select * from " + myTableName + " where Ten like \'" + comboboxValue + "\' and loai like '" + loai_value + "'";
		strSql = strSql + " and ( ploai1 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai2 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai3 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai4 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai5 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai6 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai7 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai8 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai9 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai10 like '" + phanloai_value +"')";	
		*/
		var strSql = "Ten like \'" + comboboxValue + "\' and loai like '" + loai_value + "'";
		strSql = strSql + " and ( ploai1 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai2 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai3 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai4 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai5 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai6 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai7 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai8 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai9 like '" + phanloai_value +"'";
		strSql = strSql + "  or  ploai10 like '" + phanloai_value +"')";	
		//alert(strSql);
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter(strSql).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo_pk;
			}
			catch(e){}
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";	
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		/*
		rs = db.execute(strSql);

	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    rs.close();	    
	    */
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
      
}
function myOnblurCombobox_2(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){

		mytextbox = document.getElementById(textboxId);
		
		var Object = getObject(comboboxId);
		var end = false;
		var objArr = Object.filter("Ten like \"" + comboboxValue + "\"").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
	        try{
				document.getElementById(textboxId + DM_pk).value = objArr[i].MaSo_pk;
			}
			catch(e){}
			if (mytextbox.type == "text") mytextbox.focus();
	        return;
		}
		
		mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";	
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		/*
		var rs = db.execute("select * from " + comboboxId + " where Ten like \"" + comboboxValue + "\"");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        try{
				document.getElementById(textboxId + DM_pk).value = rs.fieldByName("MaSo_pk");
			}
			catch(e){}
			if (mytextbox.type == "text") mytextbox.focus();
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	    rs.close();	 
	    */   
    }
      
}

/*thanh do add here for B3111*/
function setAttrForCombobox_MATHANG(textboxId,mySpan,comboboxId,pageSize,GetThuocAndPhongAction){
  try{
    //alert("begin setAttrForCombobox_MATHANG()");
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	// alert("begin setAttrForCombobox_MATHANG()");
	var myComboboxId = document.getElementById( prefix_component + comboboxId);
	//alert("begin setAttrForCombobox_MATHANG3():"+myComboboxId);
	//var myTextboxId = document.getElementById(textboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	// alert("begin setAttrForCombobox_MATHANG4()");
	myComboboxId.invalidMessage="";
	// alert("begin setAttrForCombobox_MATHANG5()");
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	//alert("begin setAttrForCombobox_MATHANG()");
	myComboboxId.onblur=" myOnblurCombobox_callAjax(\'" + textboxId + "\',\'" + comboboxId + "\',\'" + GetThuocAndPhongAction +  "\'); assignAltToValue(\'" + textboxId + "\')";
	//alert("begin setAttrForCombobox_MATHANG()");
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
  }catch(e){
  
    alert("setAttrForCombobox_MATHANG():"+e);
  }	
}
/*thanh do add here for B3111*/
function myOnblurCombobox_callAjax(textboxId,comboboxId,GetThuocAndPhongAction){
  try{
	var myform = document.forms[0];
    //alert("myform:"+myform);
	var validate = iesvn_ValidateRequired(myform);
	//alert("validate2:"+validate);
	if (validate == false){
	  return;
	}
	
    //alert("comboboxId:"+comboboxId);
	mycombobox = document.getElementById(prefix_component + comboboxId);
	//alert("2:" + mycombobox);
	comboboxValue = mycombobox.value;
	//alert("comboboxId:"+textboxId);
	if(comboboxValue != ""){

		mytextbox = document.getElementById(textboxId);
		var Object = getObject(comboboxId);
		var end = false;
		var temp = "";
		//alert("comboboxValue: " + comboboxValue);
		var objArr = Object.filter("Ten like ?", comboboxValue).toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
			//alert("obj.MaSo:"+obj.MaSo);
			//alert("GetThuocAndPhongAction:"+GetThuocAndPhongAction);
			temp = objArr[i].MaSo;
	        sendId_MAHANG(objArr[i].MaSo,GetThuocAndPhongAction);
	        //alert(2);
			return;
		}
		
		mytextbox.alt = "";
			mycombobox.focus();
	    	mycombobox.value = "";
		/*
		//alert("mytextbox:" + mytextbox);
		//alert("select:" + "select * from " + comboboxId + " where Ten=\"" + comboboxValue + "\"");
		var rs = db.execute("select * from " + comboboxId + " where Ten like \"" + comboboxValue + "\"");
		//alert("rs:" + rs);
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        //alert("rs.fieldByName:" +rs.fieldByName("MaSo"));
	        sendId_MAHANG(rs.fieldByName("MaSo"),GetThuocAndPhongAction);
	        //alert(1);
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }	
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    rs.close();	    
	    */
    }
   }catch(e){
     alert("myOnblurCombobox_callAjax:"+ e);
   }   
}
function myOnblurComboboxJSF(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	//alert(comboboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	




	arr2 = comboboxIdJSF.split("__");
	myTableName = "";

	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}	
	
	//alert(myTableName);
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo
	var mytextbox;
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		mytextbox = document.getElementById(textboxId);
		//alert("select * from " + myTableName + " where Ten=\'" + comboboxValue + "\'");
		var Object = getObject(myTableName);
		var end = false;
		//alert(1);
		var objArr = Object.filter("Ten like \'" + comboboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
			return;
		}
		mytextbox.alt = "";
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \'" + comboboxValue + "\'");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");	    
	        //alert(1);    
	        return;
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }    
	    */
    }
    
    try{
  		mytextbox.value = "";
		mycombobox.focus();
		mycombobox.value = "";	
	}catch(e){
	
	}
	//rs.close();	  
}


function setAttrForCombobox(textboxId,mySpan,comboboxId,pageSize){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";


	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
}

/**
  thanh do add here
**/
function getTenFromMa( tenTable , MaSo ){
	try{    
	    var ten = "";
		var Object = getObject(tenTable);
		var objArr = Object.filter("MaSo like \'" + MaSo + "\'").toArray();
		//alert(objArr);
		//alert(objArr.length);
		
		for (var i = 0; i < objArr.length; i++) {
			ten = objArr[i].Ten;
			//alert(objArr.length);
			return ten;
		}
		
		/*
			//alert("select Ten from " + tenTable + " where MaSo=\'" + MaSo + "\'");
			var rs = db.execute("select Ten from " + tenTable + " where MaSo like \'" + MaSo + "\'");
			
		    while (rs.isValidRow()) {
		 
				ten = rs.field(0);
				//alert(ten);
				break;
				
		    }
		    rs.close();
		    */
		 //return ten;   
	}catch(e){
	  alert(e + 2);
	  
	}
}

function myOnblurCombobox_Ex(textboxId,comboboxId, myTableName, tenField, giaTriField){
	//alert("myOnblurCombobox_Ex");
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		
		//alert(myTableName);
		var Object = getObject(myTableName) ;
		//alert(Object);
		mytextbox = document.getElementById(textboxId);
		//alert("comboboxValue:"+comboboxValue);
		var arrayObj =   Object.filter("Ten like ?", comboboxValue).filter(tenField + " like ?", giaTriField).toArray();
		//alert(arrayObj);
		//alert(arrayObj.length);
		
		//var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
		for ( var i = 0; i < arrayObj.length ; i ++ ) {
			 mytextbox.alt = arrayObj[i].MaSo;
			 try{
			 	//alert(100111);
				document.getElementById(textboxId + DM_pk).value = arrayObj[i].MaSo_pk;
			}
			catch(e){}
			 // alert(20000); 
	        return;
		}
	    
	    if ( arrayObj == null || arrayObj.length == 0) {
			mytextbox.alt = "";
		}
	   	mycombobox.focus();
	    mycombobox.value = "";	
	    try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
	       
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
    }
   
}


function myOnblurTextbox_Ex (textboxId,comboboxId, myTableName, tenField, giaTriField){ 

	var mytextbox = document.getElementById(textboxId);
	var textboxValue = mytextbox.value;
	
	
	if(textboxValue != ""){
		//alert(textboxValue);
		var Object = getObject(myTableName);
		//alert("myTableName"+myTableName);
		//alert("myTableName"+myTableName);
		mycombobox = dojo.byId(comboboxId);
		//alert(mycombobox);
		var objectarr = Object.filter("MaSo like ?", textboxValue ).filter(tenField + " like ?", giaTriField ).toArray();
		//var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		//alert(objectarr);
		//alert(objectarr.length);
		var end = false;
		//alert(objectarr.length);
		for (var i = 0; i < objectarr.length; i++) {
		    //alert("come here");
			mycombobox.value = objectarr[i].Ten;
			//alert("textValue: " + textboxValue + ", obj.Ten: " + obj.Ten + ", " + mycombobox.value);
			try{
				document.getElementById(textboxId + DM_pk).value = objectarr[i].MaSo_pk;
			}
			catch(e){}
			//alert(objectarr[i].Ten);
			return;
		}
		
	    	//alert("not end");
	    	mytextbox.alt = "";
	    	mycombobox.value = "";   
	    	mytextbox.value = "";
	    	try{
				document.getElementById(textboxId + DM_pk).value = "";
			}
			catch(e){}
		    
		    mytextbox.focus();
	    //alert(1);
    } else {
    	mycombobox = document.getElementById(comboboxId);
    	mycombobox.value = "";
    }
    
    
    try {
		document.getElementById(textboxId + DM_pk).value = "";
	} catch(e){}

}


function setAttrForCombobox_StoreValue_Ex(textboxId,mySpan,comboboxId,pageSize,Object, tenField, giaTriField){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ;  assignAltToValue(\'" + textboxId + "\') ; myOnblurCombobox_Ex(\'" + textboxId + "\',\'" + comboboxId + "\','DM_BENH_ICD','TacNhan','1');";
		//alert("3");
		myComboboxId.onblur=" assignAltToValue(\'" + textboxId + "\') ; myOnblurCombobox_Ex(\'" + textboxId + "\',\'" + comboboxId + "\','DM_BENH_ICD','TacNhan','1');";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("MaSo like ?", textboxValue).filter(tenField + " like ?", giaTriField).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}


function setAttrForCombobox_StoreValue(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("MaSo like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}


function setAttrForCombobox_StoreValue_KetQua(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); HSBA_Enable();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); HSBA_Enable(); ";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var object =   Object.filter("MaSo like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description);
	}		    	
}

function setAttrForCombobox_StoreValue_DmThuoc(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); getDvt();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var dmThuoc = getObject(comboboxId);
			var object =   dmThuoc.filter("MaSo like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
	}catch(e){
	  alert(e.description + 13);
	}		    	
}


function setAttrForCombobox_StoreValue_Loai(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetValueForPhanLoaiThuoc();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetValueForPhanLoaiThuoc();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			var obj =   Object.filter("MaSo like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < obj.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = obj[i].Ten;
                // alert(persons[0].firstName)
                }			
		}
	}catch(e){
	  alert(e.description + 12);
	}		    	
}

function resetValueForPhanLoaiThuoc(){

	comboboxId = "DM_PHAN_LOAI";
	var search = dijit.byId(comboboxId);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	search.store = CatalogStore;	
	
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA").value = "";
	document.getElementById( "DM_PHAN_LOAI").value = "";
	document.getElementById(prefix_component + "DM_PHAN_LOAI_MA").focus();
       
}

/**
 *
**/
function setAttrForCombobox_StoreValue_CapNhatHSBA(textboxId,mySpan,comboboxId,pageSize,Object){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');  getListBenhNhanHSBA();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
		    //alert(textboxValue);
			mycombobox = document.getElementById(comboboxId);
			//alert(2);
			var object =   Object.filter("MaSo like ?", textboxValue).toArray();
			//alert("object:"+object);
                 for (var  i = 0; i < object.length; i ++) {
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	myOption.text = object[i].Ten;
                // alert(persons[0].firstName)
                }
			
		}
		
		
	}catch(e){
	  alert(e + 3);
	}		    	
}

/*
*
*/
function resetDMHuyenXa(){

  /*
    In case of huyen or xa the same as tinh: do nothing  
  */
  var resetHuyen = true;
  var resetXa = true;
  
  var valueMaTinh = document.getElementById(prefix_component + 'TINH_MA').value;	
  if (valueMaTinh != null && valueMaTinh != ""){
  	var valueMaHuyen = document.getElementById(prefix_component + 'HUYEN_MA').value;
  	
  	//alert("valueMaTinh:"+valueMaTinh);
  	//alert("valueMaHuyen:"+valueMaHuyen);
  	if (valueMaHuyen.indexOf(valueMaTinh + ".") >= 0){
  	   resetHuyen = false;
  	}
  	
  	var valueXa = document.getElementById(prefix_component + 'XA_MA').value;
  	if (valueXa.indexOf(valueMaTinh + ".") >= 0){
  	   resetXa = false;
  	}
  }
  
  /*
    Else reset huyen and xa
  */	
  if (resetHuyen == true){
   document.getElementById('DM_HUYEN').value = "";
   resetForCombobox('DM_XA','HUYEN_MA');
  }
 
  if (resetHuyen == true){
   document.getElementById('DM_XA').value = "";
   resetForCombobox('DM_HUYEN','XA_MA');  	 	
  }
 
 document.getElementById(prefix_component + "HUYEN_MA").focus();
}

/*
*
*
*/
function resetDMXa(){
  var resetXa = true;
  
  var valueMaHuyen = document.getElementById(prefix_component + 'HUYEN_MA').value;
  if (valueMaHuyen != null && valueMaHuyen != ""){
  	var valueXa = document.getElementById(prefix_component + 'Xa_MA').value;
  	if (valueXa.indexOf(valueMaHuyen + ".") >= 0){
  	   resetXa = false;
  	}  
  }	
  
  if (resetXa == true){
  	document.getElementById('DM_XA').value = "";
 	resetForCombobox('DM_XA','XA_MA');
  }
  document.getElementById(prefix_component + "XA_MA").focus();	
}

/*
*
* 
*/
function resetForCombobox(comboboxId, textboxma){

	var search = dijit.byId(comboboxId);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	search.store = CatalogStore;	
	
	try{
		document.getElementById(prefix_component + textboxma).value = "";
	}catch(e){
	
	}
	document.getElementById( comboboxId ).value = "";
	
}

/*
*
*
*/
function setAttrForCombobox_StoreValue_Huyen_Xa(textboxId,mySpan,comboboxId,pageSize, idHuyen, idXa){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMHuyenXa();";
		//alert("3");
		myComboboxId.onblur="(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMHuyenXa();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			
			//alert("mycombobox:"+mycombobox);
			//alert("sql:"+"select Ten from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
			var arr1 = comboboxId.split("__");
			var myTableName = "";
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			//alert("myTableName:" + myTableName);
			var Object = getObject(myTableName);
			var objArr = Object.filter("MaSo like ?", textboxValue).toArray();
			for (var i = 0; i < objArr.length; i++) {
				var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
			}
			
			/*
			var rs = db.execute("select Ten from " + myTableName + " where MaSo=\'" + textboxValue + "\'");
			//alert(1000);
		    while (rs.isValidRow()) {
		    //alert(1001);
		        var myOption = document.getElementById(comboboxId + "_opt");
		        //alert(1002);
				myOption.text = rs.field(0);
				//alert(1003);
				//listCatalog(document.getElementById(comboboxId ));
				rs.next();
		    }
		    rs.close();
		    */
		}
	}catch(e){
	  alert("setAttrForCombobox_StoreValue_Huyen_Xa: " + e.description);
	}		    	
}


/*
*
*
*/
function setAttrForCombobox_StoreValue_Xa(textboxId,mySpan,comboboxId,pageSize,  idXa){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); resetDMXa();";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\');  resetDMXa();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			mycombobox = document.getElementById(comboboxId);
			
			//alert("mycombobox:"+mycombobox);
			//alert("sql:"+"select Ten from " + comboboxId + " where MaSo=\'" + textboxValue + "\'");
			var arr1 = comboboxId.split("__");
			var myTableName = "";
			//alert(comboboxIdJSF);
//Manh added
			if(arr1.length > 1){
				for(i = 0; i < arr1.length - 1; i++){
				    if (i == 0){
				       myTableName += arr1[i];
				    }else{
				    	 myTableName += "_" + arr1[i]  ;
				    }
					
				}
			}else{
			   myTableName = arr1[0];
			}
			var Object = getObject(myTableName);
			var objArr = Object.filter("MaSo like ?", textboxValue).toArray();
			for (var i = 0; i < objArr.length; i++) {
				var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
			}
			
			/*
			//alert("myTableName:" + myTableName);
			var rs = db.execute("select Ten from " + myTableName + " where MaSo=\'" + textboxValue + "\'");
//Manh end
			//alert(1000);
		    while (rs.isValidRow()) {
		    //alert(1001);
		        var myOption = document.getElementById(comboboxId + "_opt");
		        //alert(1002);
				myOption.text = rs.field(0);
				//alert(1003);
				//listCatalog(document.getElementById(comboboxId ));
				rs.next();
		    }
		    rs.close();
		    */
		}
	}catch(e){
	  alert("setAttrForCombobox_StoreValue_Xa: " + e.description);
	}		    	
}
function setAttrForCombobox_StoreValue_2(textboxId,mySpan,comboboxId,pageSize){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var objArr = Object.filter("MaSo like \'" + textboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = objArr[i].Ten;
		} 
		
		/*
		var rs = db.execute("select Ten from " + comboboxId + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = rs.field(0);
			rs.next();
	    }
	    rs.close();
	    */
	}	    	
}

function setAttrForCombobox_StoreValue_N(textboxId,mySpan,comboboxId,pageSize, nextfieldId){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]; document.getElementById('" + nextfieldId + "').focus();";
	myComboboxId.onblur="myOnblurCombobox_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
	
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	if(textboxValue != ""){
		mycombobox = document.getElementById(comboboxId);
		var Object = getObject(comboboxId);
		var objArr = Object.filter("MaSo like ?", textboxValue);
		for (var i = 0; i < objArr.length; i++) {
			var myOption = document.getElementById(comboboxId + "_opt");
				myOption.text = objArr[i].Ten;
		}
		
		/*
		var rs = db.execute("select Ten from " + comboboxId + " where MaSo like \'" + textboxValue + "\'");
	    while (rs.isValidRow()) {
	        var myOption = document.getElementById(comboboxId + "_opt");
			myOption.text = rs.field(0);
			rs.next();
	    }
	    rs.close();
	    */
	}	    	
}


function setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize){

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0];myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	myComboboxId.onblur="myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
	
}

// B4134_Tinhtonkhovagiatri
function setAttrForComboboxJSFFor_B4134_Tinhtonkhovagiatri(textboxId,mySpan,comboboxId,pageSize){
	//alert("textboxId:"+textboxId);
	
	try{    
	    
		var mySpan = document.getElementById(mySpan);
		//alert("mySpan:"+mySpan);
		mySpan.dojoType="dojo.data.ItemFileReadStore"; 
		//alert("textboxId2:"+textboxId);
		mySpan.jsId="defaultStore";
		//alert("textboxId3:"+textboxId);
		mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
		//alert("textboxId4:"+textboxId);
		var myComboboxId = document.getElementById(comboboxId);
		//alert("comboboxId:"+comboboxId)
		myComboboxId.dojoType="dijit.form.FilteringSelect";
		//alert("1");
		myComboboxId.invalidMessage="";
		//alert("2");
		myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0] ; myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
		//alert("3");
		myComboboxId.onblur="myOnblurCombobox(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\'); listPhanLoaiFocus();";
		//alert("4");
		//alert("5");
		myComboboxId.searchAttr="title";	
		//alert("6");
		myComboboxId.pageSize=pageSize;
		//alert("7");
		myComboboxId.autoComplete=false;
		//alert("8");
		mytextbox = document.getElementById(textboxId);
		
		//alert("mytextbox value:"+ mytextbox.value);
		
		textboxValue = mytextbox.value;
		if(textboxValue != ""){
			//alert("comboboxId: " + comboboxId);
			mycombobox = document.getElementById(comboboxId);
			//alert("mycombobox: " + mycombobox);
			var dm = getObject(comboboxId);
			var object =   dm.filter("MaSo like ?", textboxValue).toArray();
			//alert(object);
                 for (var  i = 0; i < object.length; i ++) {
                 	//alert("Ten: " + object[i].Ten);
                 	var myOption = document.getElementById(comboboxId + "_opt");
                 	//alert(1);
                 	
                 	//alert("myOption: " + myOption);
                 	myOption.text = object[i].Ten;
                }
			
		}
	}catch(e){
	  alert(e.description + 11);
	}
}

function  setAttrForComboboxJSFForPhieuDuTru (_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	//alert(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	//alert("2");
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPhieuDuTru(\'"+ comboboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxJSFForPhieuDuTru :" +  e);
  }
}

function  setAttrForComboboxBenhNhanTiepDon(_mySpan,comboboxId,pageSize,showId){
  try{
	var mySpan = document.getElementById(_mySpan);
	//alert(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	//alert("2");
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__benhnhanduoctiepdon_ma\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__benhnhanduoctiepdon_ma\'); mySetValueForBenhNhanTiepDon(\'"+ comboboxId + "\',\'"+ showId +"\');";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxBenhNhanTiepDon :" +  e.description);
  }
}

/**
 *
**/
function mySetValueForBenhNhanTiepDon (comboboxId,showId) { // showId = __matiepdon or __sobenhan

   //alert("call me");
   var myValue = document.getElementById(prefix_component + '__benhnhanduoctiepdon_ma').value;
   //alert(myValue);
   if (myValue == null || myValue ==""){
       try{
   			 document.getElementById(prefix_component + showId).value = "";   
   	   }catch(e){
       		return;  
       }    
   }
   //alert("myValue:"+myValue);
   var i = myValue.indexOf("___"); //3 _
   var maTiepDon = myValue.substring(0,i);   
   //alert("maTiepDon:"+maTiepDon);
   
   try{
   	document.getElementById(prefix_component + showId).value = maTiepDon;  
   }catch(e){
  	  
   }
   
  
}
 
function mySetValueForPhieuDuTru(comboboxId){
	try{ 
	   //alert("alert in mySetValueForPhieuDuTru with param : " + comboboxId );
	   //alert("value of me: " + document.getElementById(prefix_component + '__listtonkho_ma_2').value );
	   
	   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
	   //alert(myValue);
	   if (myValue == null || myValue ==""){
	      return;
	   }
	   
	   var i = myValue.indexOf("___"); //3 _
	   var maHang = myValue.substring(0,i);   
	   //alert("maHang:"+maHang);
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var tenHang = myValue.substring(0,i);
	   //alert("tenHang:"+tenHang);
	   document.getElementById(prefix_component + "__tenhang").value = tenHang;
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var quyCach = myValue.substring(0,i);
	   //alert("quyCach:"+quyCach);
	   document.getElementById(prefix_component + "__quycach").value = quyCach;
	   //document.getElementById(prefix_component + "__quycach").focus();
	   myValue = myValue.substring(i+3);
	   
	   i = myValue.indexOf("___"); //3 _
	   var donVi = myValue.substring(0,i);
	   //alert("donVi:"+donVi);
	   document.getElementById(prefix_component + "__donvi").value = donVi;
	   myValue = myValue.substring(i+3);  
	   
	   i = myValue.indexOf("___"); //3 _
	   var tonKho = myValue.substring(0,i);
	   //alert("tonKho:"+tonKho);
	   document.getElementById(prefix_component + "__tonkho").value = tonKho;
	   document.getElementById(prefix_component + "__tonkho_hid").value = tonKho;
	   
	   myValue = myValue.substring(i+3);   
	  // document.getElementById(prefix_component + "__xinlinh").focus();
	
	   var donGia = myValue;
	   //alert("donGia:"+donGia);
	    document.getElementById(prefix_component + "__dongia").value = donGia;
     }catch(e){
       alert("error at mySetValueForPhieuDuTru: " + e.description);
     }  
   
   
}

// Manh added

function listCatalogJSF_PDTRU(myCombobox, data){
	
	var myComboboxId = document.getElementById(myCombobox);
	

	//alert("comboboxId:"+ myComboboxId);
	
   // var rs ;
	try{
	   
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		//alert(100);
		var i = 0;
		var havingData = false;
		if (data != null){
			while (data.record[i] != null){            	
	                data1 = data.record[i];   
	                //alert(data1);             
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert(myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                     havingData == true;
	                }
					i=i+1;
	         }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         		data1 = data.record;   
	                //alert(data1);             
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert(myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                     havingData == true;
	                }
	         
	         }
	    }else{
	   	  // CatalogStore.newItem({id: "", title: ""});
	   	    //alert("myComboboxId.options:"+myComboboxId.options);
	    	//myComboboxId.options.length = 0;
	    	myComboboxId.value = "";
	    }
	     if (havingData == false){
	    	myComboboxId.value = "";
	    }
	         
		//alert(103);
		search.store = CatalogStore;	
		//alert(104);
	} catch(e){
	   alert("catch .... in....listCatalogJSF_PDTRU():"+e );
	}
	
	

}

function listCatalogDanhSachBenhNhanTiepDon(myCombobox, data){
	
	var myComboboxId = document.getElementById(myCombobox);
	

	//alert("comboboxId:"+ myComboboxId);
	
   // var rs ;
	try{
	   
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		//alert("data:"+data);
		var i = 0;
		var havingData = false;
		if (data != null){
			
			while (data.record[i] != null){            	
	                data1 = data.record[i];  
	                
	                //alert(data1);         
	                if(data1.matiepdon != null){       
	                  var myId =  data1.matiepdon + "___" + data1.hoten + "___" + data1.tuoi + "___" + data1.namsinh + "___" + data1.cmnd; 
	                  
					  //alert(myId);  	
					  var hoten = data1.hoten;
					  if (hoten == null || hoten == "null" || hoten == ""){
					    i=i+1;
					    continue;
					  }
					  
					   var tuoi = data1.tuoi;
					  if (tuoi == null || tuoi == "null" || tuoi == ""){
					    tuoi = "";
					  }
					  
					   var namsinh = data1.namsinh;
					 if (namsinh == null || namsinh == "null" || namsinh == ""){
					    namsinh = "";
					  }
					   var cmnd = data1.cmnd;
					 if (cmnd == null || cmnd == "null" || cmnd == ""){
					    cmnd = "";
					  }
	                  CatalogStore.newItem({id: myId , title:   hoten + " " + tuoi + " " + namsinh + " " + cmnd  });  
	                  // alert(102);
	                  havingData == true;
	                }
					i=i+1;
	         }
	         
	         if (i == 0) { // truong hop nay chi co' 1 record
	         
	          data1 = data.record;  
	                
	                //alert(data1);         
	                if(data1.matiepdon != null){       
	                  var myId =  data1.matiepdon + "___" + data1.hoten + "___" + data1.tuoi + "___" + data1.namsinh + "___" + data1.cmnd ; 
	                  
					  //alert(myId);  	
					  var hoten = data1.hoten;
					  if (hoten == null || hoten == "null" || hoten == ""){
					   
					  }else{
					  
						   var tuoi = data1.tuoi;
						  if (tuoi == null || tuoi == "null" || tuoi == ""){
						    tuoi = "";
						  }
						  
						   var namsinh = data1.namsinh;
						 if (namsinh == null || namsinh == "null" || namsinh == ""){
						    namsinh = "";
						  }
						   var cmnd = data1.cmnd;
						 if (cmnd == null || cmnd == "null" || cmnd == ""){
						    cmnd = "";
						  }
		                  CatalogStore.newItem({id: myId , title:   hoten + " " + tuoi + " " + namsinh + " " + cmnd  });  
		                  // alert(102);
		                  havingData == true;
	                  }
	                }
	         
	         }
	         
	    }else{
	   	  // CatalogStore.newItem({id: "", title: ""});
	   	    //alert("myComboboxId.options:"+myComboboxId.options);
	    	//myComboboxId.options.length = 0;
	    	myComboboxId.value = "";
	    }
	    if (havingData == false){
	    	myComboboxId.value = "";
	    }     
		//alert(103);
		search.store = CatalogStore;	
		//alert(104);
	} catch(e){
	   alert("catch .... in....listCatalogDanhSachBenhNhanTiepDon():"+e.description );
	}
	
	

}
//Manh end

function listCatalogJSF_PXUAT(myCombobox, data){
	//alert("listCatalogJSF_PXUAT");
	var myComboboxId = document.getElementById(myCombobox);
	

	//alert("comboboxId:"+ myComboboxId);
	//alert("data:" + data);
	var havingData = false;
   // var rs ;
   if (data != null){
			try{
				var search = dijit.byId(myComboboxId.id);
				//alert("myComboboxId.id:"+myComboboxId.id);
				var jsonData = { identifier: "id", items: [], label: "title" };
				var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
				CatalogStore.newItem({id: "", title: ""});
				
				//alert(100);
				var i = 0;
				
				while (data.record[i] != null){      // truong hop nay co' >=2 record      	
		                data1 = data.record[i];   
		                //alert("while");             
			             if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;  
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                   CatalogStore.newItem({id: myId , title: myTitle  });  
			                  // alert(102);
			                   havingData == true;
			             }
							i=i+1;
		          }
		            
		          if (i == 0) { // truong hop nay chi co' 1 record
		            	data1 = data.record;   
		                //alert(101);             
			             if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;   
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                  CatalogStore.newItem({id: myId, title: myTitle});			                   
			                   //CatalogStore.newItem({id: "id" , title: "title" }); 
			                  havingData == true;
			             }
		          }
				//alert(103);
				search.store = CatalogStore;	
				//alert("CatalogStore:"+CatalogStore);
				//alert("end");
			} catch(e){
			   alert("catch .... in....listCatalogJSF_PXUAT():"+ e.description );
			}
	
	}
	
	 if (havingData == false){
	    	myComboboxId.value = "";
	  }  

}

function formatString(str, size) {
	if (str) {
		for (var i = str.length; i < size; i++) {
			str = str + " ";
		}
	} else {
		str = "";
	}
	return str;
}

function setAttrForComboboxJSFForThuoc(textboxId,mySpan,comboboxId,pageSize){
	//alert(comboboxId);

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	//alert("2   "+myComboboxId);
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\','',''); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}

function  setAttrForComboboxJSFForPhieuXuat (_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	//alert(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	//alert("2");
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="document.getElementById(prefix_component + \'__listtonkho_duocpham_ma\').value = arguments[0]";
	myComboboxId.onblur="mySetValueForPhieuXuat(\'"+ comboboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }catch(e){
    alert("error at setAttrForComboboxJSFForPhieuXuat :" +  e);
  }
}

function mySetValueForPhieuXuat(comboboxId){
   //alert("alert in mySetValueForPhieuDuTru with param : " + comboboxId );
   //alert("value of me: " + document.getElementById(prefix_component + '__listtonkho_ma_2').value );
   
   
   //alert("document.getElementById(prefix_component + '__listtonkho_duocpham_ma'):"+document.getElementById(prefix_component + '__listtonkho_duocpham_ma'));
   var myValue = document.getElementById(prefix_component + '__listtonkho_duocpham_ma').value;
   //alert("myValue:"+myValue);
   if (myValue == null || myValue == ""){
      //document.getElementById(prefix_component + 'DM_THUOC_MASO').focus();
      //alert(1);
      clearDmt();
      return;
   }
   
   
   var i = myValue.indexOf("___"); 
   var tonKhoMa = myValue.substring(0,i);   
   myValue = myValue.substring(i+3);
  // alert('tonKhoMa: ' + tonKhoMa);
   document.getElementById(prefix_component + "__tonkhoma_hid").value = tonKhoMa;
   
   
   i = myValue.indexOf("___"); 
   var soluongton = myValue.substring(0,i);
   if (soluongton == "" || soluongton == "null" || soluongton == "NULL") {
   		document.getElementById(prefix_component + "__tonkho_hid").value = "";
   		document.getElementById(prefix_component + "__tonkho").value = "";
   } else {
   		document.getElementById(prefix_component + "__tonkho_hid").value = parseInt(soluongton);
   		document.getElementById(prefix_component + "__tonkho").value = parseInt(soluongton);
   }
   
}

function setAttrForComboboxJSFForThuoc_PhieuDuTru(textboxId,mySpan,comboboxId,pageSize){
	//alert(comboboxId);

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	//alert(myComboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	//alert("2   "+myComboboxId);
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\','',''); assignAltToValue(\'" + textboxId + "\') ; getTonkho(); ";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}
//Manh 
function set_diengiai(textboxId,comboboxId) {
	//alert("2");
    try {
		if (document.getElementById(prefix_component + "__tem") == null || document.getElementById(prefix_component + "__tem") == "undefined"){
		  return ;
		}
		var diengiai = document.getElementById( comboboxId).value;
		document.getElementById(prefix_component + "__tem").value = diengiai;
		//alert(document.getElementById(prefix_component + "__tem").value);
	
	}catch(e){
	   alert('Exception in function set_diengiai');
	}
}
function setAttrForComboboxJSF_CLS(textboxId,mySpan,comboboxId,pageSize){
    
    //alert('thanh01');

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	//alert(20);
	

	myComboboxId.onblur="set_diengiai(\'" + textboxId + "\',\'" + comboboxId + "\');; myOnblurComboboxJSF(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	
	//alert(myComboboxId.onblur);
	//alert(21);
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.autoComplete=false;
	myComboboxId.pageSize=pageSize;
	
}

function setAttrForComboboxJSF_DMTHUOC(textboxId,mySpan,comboboxId,pageSize){
    
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	
	/*
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	*/
	//thanhdo add here
	//temp = myTableName.split(":");
	//myTableName = temp[temp.length - 1];
	//end thanhdo

	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="textHideFocus();myOnblurTextboxForDmThuoc(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;

}
/*
// Phuc add code below 
function getCatalogFromServer_2(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
      
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){            	
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,""]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/
function myOnblurComboboxJSF_2(textboxId,comboboxId){
	mycombobox = document.getElementById(comboboxId);
	//alert(comboboxId);
	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];

	arr2 = comboboxIdJSF.split("__");
	myTableName = "";

	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}	
			
	comboboxValue = mycombobox.value;
	if(comboboxValue != ""){
		mytextbox = document.getElementById(textboxId);
		//alert("select * from " + myTableName + " where Ten=\'" + comboboxValue + "\'");
		var Object = getObject(myTableName);
		var end = false;
		var objArr = Object.filter("Ten like \'" + comboboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			var obj = objArr[i];
			mytextbox.alt = obj.MaSo;
	        if (mytextbox.type == "text") mytextbox.focus();
	        return;
		}
		mytextbox.alt = "";
			 mytextbox.value = "";
	    	mycombobox.focus();
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \'" + comboboxValue + "\'");		
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        if (mytextbox.type == "text") mytextbox.focus();
	        return;
	        rs.next();
	    }
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    }    

	    mytextbox.value = "";
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    rs.close();	 
	    */   
    }else{
    	
    }
      
}

function setAttrForComboboxJSF_2(textboxId,mySpan,comboboxId,pageSize){

	arr1 = comboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurComboboxJSF_2(\'" + textboxId + "\',\'" + comboboxId + "\'); assignAltToValue(\'" + textboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;	
}
// code new
function setAttrForComboboxJSF_ext(textboxId,mySpan,comboboxId,pageSize){
        setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize);
        setTimeout(function() {iesvn_LoadAgain();},2000);
}

// Phuc end add code

function setAttrForJSFCombobox(comboboxId,pageSize){
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.autoComplete=false;
	myComboboxId.pageSize=pageSize;
}
// code new
function setAttrForComboboxJSF_ext(textboxId,mySpan,comboboxId,pageSize){
        setAttrForComboboxJSF(textboxId,mySpan,comboboxId,pageSize);
        setTimeout(function() {iesvn_LoadAgain();},2000);
}

// Phuc end add code

function setAttrForJSFCombobox(comboboxId,pageSize){
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.pageSize=pageSize;
}
function listCatalog(myCombobox){
   try{
	//alert(0);
    //alert(1);
	myComboboxId = myCombobox.id;
	//alert(myComboboxId);
	arr1 = myComboboxId.split(":");
	//alert(arr1.length);
	//alert("myComboboxId:"+myComboboxId);
	
	if(arr1.length == 1){
	    //alert("myCombobox: "+myCombobox);
	    
	    //alert(myComboboxId);
	    if (myComboboxId == "DM_HUYEN"){
	    	//alert(2);
	    	listCatalog_Huyen_Xa(myCombobox,"TINH_MA");
	    }else if (myComboboxId ==  "DM_XA"){
	    	listCatalog_Huyen_Xa(myCombobox,"HUYEN_MA");
	    }else if (myComboboxId ==  "DM_DONVIDUPHONG"){
	    	listCatalogDonViByTuyen_CT(myCombobox,"TUYEN_MA", "CHUONGTRINH_MA");
	    }else if(myComboboxId == "DM_TTGDCAP2"){
	    	listCatalog_Huyen_Xa(myCombobox,"TTGDCAP1_MA");	    
	    }else if (myComboboxId ==  "__listtonkho"){
	        
			listCatalogSearchForPDTRU(myCombobox);

		}else if (myComboboxId ==  "__listtonkhoBhyt"){
	        
			listCatalogSearchForPhieuXuatBh(myCombobox);
		}
		else if (myComboboxId ==  "__benhnhanduoctiepdon"){
	        
			//ds benh nhan

		}else if (myComboboxId ==  "__listtonkho_duocpham"){
	        
			listCatalogSearchForPXUAT(myCombobox);

		}else if ( myComboboxId ==  "DM_THUOC"){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	if (loaiObj == null){
	    		loaiObj = document.getElementById(prefix_component + "DTDMLOAI_MA");
	    	}
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
	    	var phanLoaiObj = document.getElementById(prefix_component + "DM_PHAN_LOAI_MA");
	    	var valuePhanLoai = "";
	    	if(phanLoaiObj){
	    		valuePhanLoai = phanLoaiObj.value;
	    	}
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DM_Thuoc(myCombobox,valueLoai,valuePhanLoai);	    
	    }else if ( myComboboxId ==  "DM_PHAN_LOAI"){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
   	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalog_DM_PHAN_LOAI(myCombobox,valueLoai);	    
	    }else if (myComboboxId.indexOf("DM_BENH_ICD") >= 0){
			listCatalogWithLongData(myCombobox,"DM_BENH_ICD");
		}
		else if (myComboboxId.indexOf("DM_TAC_NHAN") >= 0){
			//alert(1);
			listCatalog_Ex(myComboboxId,"DM_BENH_ICD",'TacNhan', '1');
			
		}
		else if (myComboboxId.indexOf("DT_DM_PHAU_THUAT") >= 0){
			listCatalogWithLongData(myCombobox,"DT_DM_PHAU_THUAT");
		}else {
		    
			listCatalogNormal(myCombobox);
			
		}	
		
		
	}
	else {
	    //alert(myComboboxId);
	    if (myComboboxId == prefix_component + "DM_HUYEN"){
	    	//alert(2);
	    	listCatalogJSF_Huyen_Xa(myCombobox,"TINH_MA");
	    }else if (myComboboxId == prefix_component + "DM_XA"){
	    	listCatalogJSF_Huyen_Xa(myCombobox,"HUYEN_MA");
	    
	    }else if (myComboboxId == prefix_component + "DM_THUOC" ){
	    	//alert(myComboboxId);
	    	var loaiObj = document.getElementById(prefix_component + "DT_DM_LOAI_MA");
	    	if (loaiObj == null){
	    		loaiObj = document.getElementById(prefix_component + "DTDMLOAI_MA");
	    	}
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	
	    	var phanLoaiObj = document.getElementById(prefix_component + "DM_PHAN_LOAI_MA");
	    	var valuePhanLoai = "";
	    	if(phanLoaiObj){
	    		valuePhanLoai = phanLoaiObj.value;
	    	}
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DM_Thuoc(myCombobox,valueLoai,valuePhanLoai);	    
	    }
	    
	    
	    else if (myComboboxId == prefix_component + "DT_DM_KY_THUAT"){
	    
	    	var loaiObj = document.getElementById(prefix_component + "__loai");
	    	
	    	var valueLoai = "";
	    	if(loaiObj){
	    		valueLoai = loaiObj.value;
	    	}
	    	//alert(valueLoai);
	    	
	    	//alert("myComboboxmyCombobox:"+myCombobox);
	    	listCatalogJSF_DT_DM_KY_THUAT(myCombobox,valueLoai);	    
	    }else {
	    	//alert(1);
			listCatalogJSF(myCombobox);
			//alert(0);
		}	
	}	
  }catch(ex){
     alert("error at listcatalog:" + ex.description);
  }	
}

function listCatalogSearchForPDTRU(myCombobox){
  
 //alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    //var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
		    
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		if (dataList == null){
		   //alert("dataList is null, return");
		   return;
		}   		
		   
		var i = 0;
		var data = dataList;
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		   
		
            
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		//while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			//CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			//rs.next();
			
			//i++;
			
		while (data.record[i] != null){            	
                data1 = data.record[i];   
                
                //alert(101);     
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                
                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
                
                if ( myComboboxValue == "" || iIndex == 0 ){
                    
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert("myId:" + myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                }
	            }    
				i=i+1;
            }
        if (i == 0) { // truong hop nay chi co' 1 record
         	data1 = data.record;   
                //alert(101);     
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                
                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
                
                if ( myComboboxValue == "" || iIndex == 0 ){
                    
	                if(data1.MaHang != null){       
	                  var myId =  data1.MaHang + "___" + data1.TenHang + "___" + data1.QuyCach + "___" +
						data1.DonVi + "___" +  data1.TonKho + "___" + data1.DonGia;      
						//alert("myId:" + myId);  	
	                    CatalogStore.newItem({id: myId , title:   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi + "  " + data1.DonGia});  
	                  // alert(102);
	                }
	            }    
        }   
            
		//try{
		//	rs.close();
		//}catch(e){
		   //alert("rs close(): " + e);
		//}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogSearchForPDTRU():"+e );
	}
	
}

function listCatalogSearchForPXUAT(myCombobox){
  
 //alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    //var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
		    
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		if (dataList == null){
		   //alert("dataList is null, return");
		   return;
		}   		
		   
		var i = 0;
		var data = dataList;
		
		//alert();
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		   
		
            
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		//while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			//CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			//rs.next();
			
			//i++;
		var  havingData = false;	
		//alert("data.record[i]:"+data.record[i]);
		
		
			while (data.record[i] != null){            	
	                data1 = data.record[i];
	                //alert("data1:"+data1);   
	                //alert(101);     
	                // var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuyCach + "  " + data1.DonVi; 
	                // var iIndex = varTitle.indexOf (myComboboxValue);
	                
	                //alert( "iIndex:" + iIndex +"," + "myComboboxValue:"+myComboboxValue);
	                
	                //if ( myComboboxValue == "" || iIndex == 0 ){
	                    //alert(1);
		                if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;   
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                   CatalogStore.newItem({id: myId , title: myTitle  });  
			                  // alert(102);
			                   havingData = true;
			             }
			             
		            //}    
					i=i+1;
	            }
	            
	              if (i == 0) { // truong hop nay chi co' 1 record
	              	data1 = data.record;  
	              	if(data1.MaHang != null){       
			                  var myId =  data1.TonKhoMa + "___" + data1.TonKho + "___" + i;   
			                  
			                  var tenHang = formatString(data1.TenHang, 30);
			                  var hamLuong = formatString(data1.HamLuong, 10);
			                  var tonKho = formatString(data1.TonKho, 15);
			                  var nuocSx = formatString(data1.NuocSx, 10);
			                  var hangSx = formatString(data1.HangSx, 10);
			                  var donVi = formatString(data1.DonVi, 10);
			                  
			                  var myTitle =  tenHang + " " + 
			                  					hamLuong + " " + 
			                  					tonKho + " " + 
			                  					donVi + " " + 
			                  					nuocSx + " " +
			                  					hangSx;
			                  //alert("myId:"+myId);    	
			                  //alert("myTitle:"+myTitle);
			                   CatalogStore.newItem({id: myId , title: myTitle  });  
			                  // alert(102);
			                   havingData = true;
			             }	              
	              
	              }
		//try{
		//	rs.close();
		//}catch(e){
		   //alert("rs close(): " + e);
		//}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	  
	} catch(e){
	   alert("catch .... in....listCatalogSearchForPXUAT():"+e );
	}
	
}
/**
 Used for LAP PHIEU DU TRU:  phan loai got depends on DM_LOAI
*/
function listCatalog_DM_PHAN_LOAI(myCombobox,loai_value){
  
    //alert(loai_value);

	if (loai_value == ""){
	  loai_value = "%";
	}

	comboboxId = myCombobox.id;
	

	var dmArray =   DmPhanLoai.filter("Loai like ?", loai_value).toArray();
	//alert(dmArray);
    var search = dijit.byId(myCombobox.id);
   
   
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmArray != null ) {
         
    	 for (var  i = 0; i < dmArray.length; i ++) {
            CatalogStore.newItem({id: dmArray[i].MaSo , title:   dmArray[i].Ten});
            //alert(dmArray[i].MaSo);
         }
    }
    //search.store = null;
   	search.store = CatalogStore;	
   
   
		
		//alert("myTableName2:"+myTableName);
	
	
	

}
function listCatalogJSF_DM_Thuoc(myCombobox,loai_value, phanloai_value){
	
	if (loai_value == ""){
	  loai_value = "%";
	}
	if (phanloai_value == ""){
	  phanloai_value = "%";
	}
	// alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	if (myCombobox.getDisplayedValue() == null || myCombobox.getDisplayedValue() == ''){
	  resetForCombobox(myCombobox.id,'');
	  return;
	}

	//alert("myTableName2:"+myTableName);
	var dmthuocArray =   DmThuoc.filter("Ten like ?", myCombobox.getDisplayedValue() + "%").filter("loai like ?", loai_value).filter("(ploai1 like ? OR ploai2 like ? OR ploai3 like ? OR ploai4 like ? OR ploai5 like ? OR ploai6 like ? OR ploai7 like ? OR ploai8 like ? OR ploai9 like ? OR ploai10 like ?)", phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value,phanloai_value).toArray();
    var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmthuocArray != null ) {
    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
            CatalogStore.newItem({id: dmthuocArray[i].MaSo , title:   dmthuocArray[i].Ten});
         }
    }
   	search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	
	

}


function listCatalogWithLongData(myCombobox,ten_danh_muc){

	
	comboboxId = myCombobox.id;
	
	
	if (myCombobox.getDisplayedValue() == null || myCombobox.getDisplayedValue() == ''){
	  resetForCombobox(myCombobox.id,'');
	  return;
	}

	//alert("comboboxId:"+comboboxId);
	var obj = getObject(ten_danh_muc);
	var dmthuocArray =   obj.filter("Ten like ?", myCombobox.getDisplayedValue() + "%").toArray();
    var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
    if ( dmthuocArray != null ) {
    	 for (var  i = 0; i < dmthuocArray.length; i ++) {
            CatalogStore.newItem({id: dmthuocArray[i].MaSo , title:   dmthuocArray[i].Ten});
         }
    }
   	search.store = CatalogStore;	

}
function listCatalogJSF_DT_DM_KY_THUAT(myCombobox,chuDau){

	if (chuDau == ""){
	  chuDau = "%";
	}

	// alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	//alert("myTableName2:"+myTableName);
	
    var rs ;
	try{
	//alert("myTableName3:"+myTableName);
	//alert("myCombobox.getDisplayedValue():"+myCombobox.value);
	
		// alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' where loai ='" + loai_value +"'");
		
		
		//var strSql = "select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() +  "%\' and ( ChuDau like '" + chuDau + "' or ChuDauFather like '" + chuDau + "' )";
		var strSql = "Ten like \'" + myCombobox.getDisplayedValue() +  "%\' and ( ChuDau like '" + chuDau + "' or ChuDauFather like '" + chuDau + "' )";
		//alert(strSql);
		
		//rs = db.execute(strSql);	   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		var Object = getObject(myTableName);
		var objArr = Object.filter(strSql).toArray();
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].MaSo, title:   objArr[i].Ten});
		}
		
		/*
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   //alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		*/
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogJSF_DT_DM_KY_THUAT():"+e );
	}
	
	

}

function listCatalogNormal(myCombobox){

    //alert("myCombobox:"+myCombobox);
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");

    //alert("comboboxId:"+comboboxId);
    
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	//alert("myTableName: " + myTableName);
	var Object = getObject(myTableName);
	/*
	if (myTableName == "DM_QUOC_GIA" ) {
		Object = DmQuocGia.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%").toArray();
	} else if ( myTableName == "DT_DM_NOI_BAN" ) {
		Object = DtDmNoiBan.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%").toArray();
	}
	else if ( myTableName == "DM_KINH_PHI" ) {
		//alert(100);
		Object = DmKinhPhi.filter("Ten like ?", myCombobox.getDisplayedValue() + "%").toArray();
	}
	else if ( myTableName == "DT_DM_NHAN_VIEN" ) {
		Object = DtDmNhanVien.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%").toArray();
	}
	else if ( myTableName == "DM_THUOC" ) {
		Object = DmThuoc.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%").toArray();
	}
	else if ( myTableName == "DT_DM_NGUON" ) {
		Object = DtDmNguon.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%").toArray();
	}
	else if ( myTableName == "DM_KHO" ) {
		Object = DmKho.filter("Ten like ?", myCombobox.getDisplayedValue() + "%").toArray();
	}
	else if ( myTableName == "DT_DM_LOAI" ) {
		Object = DtDmLoai.filter("Ten like ?", myCombobox.getDisplayedValue() + "%" ).toArray();
	}
	else if ( myTableName == "DT_DM_HANG_SX" ) {
		Object = DtDmHangSx.filter("Ten like ?", myCombobox.getDisplayedValue() + "%" ).toArray();
	}
	else if ( myTableName == "DT_DM_DVT" ) {
		Object = DtDmNguon.filter("Ten like ?", myCombobox.getDisplayedValue()+ "%" ).toArray();
	}
	*/
	//alert("myTableName2:"+myTableName);
	if ( Object != null ) {
		//alert(Object);
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		var objArr = Object.all().toArray();
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].MaSo, title:   objArr[i].Ten});
		}
		
		/*
		for (var  i = 0; i < Object.length; i ++) {
			if ( Object[i] != null && typeof (Object[i]) == "object" ) {
				//alert(Object[i].MaSo);
          	 	CatalogStore.newItem({id: Object[i].MaSo, title:   Object[i].Ten});
			}
		}
		*/
		search.store = CatalogStore;	
        }
      
        
}
 

//code Ngoc add
function listCatalogDonViByTuyen_CT(myCombobox, tuyenid, chuongtrinhid){
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");
	
	//ngoc added
		tuyenTextbox = document.getElementById(prefix_component + tuyenid);
		tuyenma = tuyenTextbox.value;
		chuongtrinhTextbox = document.getElementById(prefix_component + chuongtrinhid);
		chuongtrinhma = chuongtrinhTextbox.value;
		//if (tuyenma == null || tuyenma == ""){
			//tuyenma.value ="";
		//	document.getElementById(prefix_component + father_id).focus();
		//	return;	
		//}
		//end ngoc added

	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}

	

    var rs ;
    var rs1;
	try{
			//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  TuyenMa like \'" + tuyenma + "%\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );
			rs1 = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  MaSo = \'" + tuyenma + "\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );   
		   	rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\' and  TuyenMa = \'" + tuyenma + "\' and  ChuongtrinhMa like \'" + chuongtrinhma + "%\'" );	
		   		
		   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs1.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs1.fieldByName("MaSo"), title:   rs1.fieldByName("Ten")});
			rs1.next();
			
			//i++;
			
		}
		try{
			rs1.close();
		}catch(e){
		   alert("rs1 close(): " + e);
		}
		
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		
		search.store = CatalogStore;	
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	   alert("catch .... in....listCatalogNormal():"+e );
	}
}
function listCatalog_Huyen_Xa(myCombobox , father_id){ //father_id == TINH_MA or father_id == HUYEN_MA
  
    try{
		comboboxId = myCombobox.id;
		arr1 = comboboxId.split(":");
	
		//thanh added
		fathertextbox = document.getElementById(prefix_component + father_id);
		value_father = fathertextbox.value;
		if (value_father == null || value_father == ""){
			fathertextbox.value = "";
			document.getElementById(prefix_component + father_id).focus();
			return;	
		}
		//end thanh added
		
		comboboxIdJSF = arr1[arr1.length - 1];
		arr2 = comboboxIdJSF.split("__");
		myTableName = "";
		if(arr2.length > 1){
			for(i = 0; i < arr2.length - 1; i++){
			    if (i == 0){
			       myTableName += arr2[i];
			    }else{
			     myTableName += "_" + arr2[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr2[0];
		}
	
		
	    var rs ;
	
		 //alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");   
		   	//rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	
		 //rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'" );	   	
	   	
		   
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		var Object = getObject(myTableName);
		//alert(value_father);
		var objArr = Object.filter("RefMa = \'" + value_father + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			CatalogStore.newItem({id: objArr[i].MaSo, title:   objArr[i].Ten});
			//alert(objArr[i].Ten);
		}
	
		/*
		//alert("myTableName---:"+myTableName);
		//var i = 0;
		while (rs.isValidRow()) {
		
		    //alert(rs.fieldByName("Ten"));
			CatalogStore.newItem({id: rs.fieldByName("MaSo"), title:   rs.fieldByName("Ten")});
			rs.next();
			
			//i++;
			
		}
		try{
			rs.close();
		}catch(e){
		   alert("rs close(): " + e);
		}
		//alert("myTableName1:"+myTableName);
		*/
		search.store = CatalogStore;
		//alert(1);
		//document.getElementById(prefix_component + XA_MA).focus();	
		
		
		//alert("myTableName2:"+myTableName);
	
	} catch(e){
	  // alert("catch .... in....listCatalogNormal():"+e );
	}
}


function listCatalogJSF(myCombobox){
    //alert(1);
	myComboboxId = myCombobox.id;
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	/*
	try{
		//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");
	   	rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'");	   	
	   
	   }
	   catch(e){
	    alert(e);
	   }
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myTableName);
	var objArr = Object.filter("Ten like \'" + myCombobox.getDisplayedValue() + "%\'").toArray();
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].MaSo, title:   objArr[i].Ten});
	}
		
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MaSo"), title: rs.fieldByName("Ten")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}

function listCatalogJSF_Huyen_Xa(myCombobox, father_id){ //father_id == TINH_MA or father_id == HUYEN_MA
    //alert(1);
	myComboboxId = myCombobox.id;
	
	//thanh added
	fathertextbox = document.getElementById(prefix_component + father_id);
	value_father = fathertextbox.value;
	if (value_father == null || value_father == ""){
		fathertextbox.value = "";
		try{
		document.getElementById(prefix_component + father_id).focus();
		}catch(e){
		  //in case of hidden element
		}
		return;	
	}
	//end thanh added
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
			
		}
	}else{
	   myTableName = arr2[0];
	}
	
	arr1 = myComboboxId.split(":");
	comboboxIdJSF = arr1[arr1.length - 1];
	/*
	try{
		//alert("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'");
	   	rs = db.execute("select MaSo, Ten from " + myTableName + " where Ten like \'" + myCombobox.getDisplayedValue() + "%\'" + " and  RefMa like \'" + value_father + "%\'" );	   	
	   
	   }
	   catch(e){
	    alert(e);
	   }
	   */
	var search = dijit.byId(myCombobox.id);
	var jsonData = { identifier: "id", items: [], label: "title" };
	var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
	CatalogStore.newItem({id: "", title: ""});
	
	var Object = getObject(myTableName);
	var objArr = Object.filter("RefMa like \'" + value_father + "%\'").toArray();
	//alert(1);
	for (var i = 0; i < objArr.length; i++) {
		CatalogStore.newItem({id: objArr[i].MaSo, title:   objArr[i].Ten});
	}
		
	
	/*
	while (rs.isValidRow()) {
		CatalogStore.newItem({id: rs.fieldByName("MaSo"), title: rs.fieldByName("Ten")});
		rs.next();
	}
	rs.close();
	*/
	search.store = CatalogStore;	
}

function assignAltToValue(compId){
	myComp = document.getElementById(compId);
	if(myComp.alt != "undefined"){
		myComp.value = myComp.alt;
	}
}

function createXmlHttpRequest() {
    var xmlHttp;
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
    return xmlHttp;
}

/*
function getCatalogFromServerIfNotExisted(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select count(*)  from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
      alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        if (myCondition > 0){
          return;
        }
        rs.next();
    }
    rs.close(); 
    
    url = myContextPath + "/actionServlet?urlAction=" + urlAction ;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,""]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/
function handlerReadyStateChangeForGetPriceKyThuatFromServer(xmlHttp){
	if (xmlHttp.readyState == 4 && xmlHttp.status == 200){
	

  var jsonExpression = "(" + xmlHttp.responseText + ")";
	//alert(jsonExpression);
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		var i = 0;
		    if(typeof(data.list) == "object"){
		        while (data.list.record[i] != null){
		                
		                data1 = data.list.record[i];
		                //alert("data1:"+data1); 
		                //alert("data1.MaSo:"+data1.MaSo);
		                if(data1.MaSo != null && data1.MaSo != ''){
		                    //alert("into if ");
		                    var donGia;
		                    var donGiaBH;
		                    var donGiaYC;
		                    var donGiaMP;
		                    //alert("2");
		                    donGia = unescape(data1.DonGia);
		                    //alert("22");
		                    donGiaBH = unescape(data1.DonGiaBH);
		                    //alert("23");
		                    donGiaYC = unescape(data1.DonGiaYC);
		                    //alert("24");
		                    donGiaMP = unescape(data1.DonGiaMP);
		                    //alert("25");
		                    document.getElementById(prefix_component + "hid_Gia").value = donGia;
		                    //alert("26");
		                    document.getElementById(prefix_component + "hid_GiaBH").value = donGiaBH;
		                    //alert("27");
		                    document.getElementById(prefix_component + "hid_GiaYC").value = donGiaYC;
		                 	//alert("28");
		                 	document.getElementById(prefix_component + "hid_GiaMP").value = donGiaMP;
		                 	
		                 	//alert("donGia:"+donGia);
		                 	//alert("donGiaBH:"+donGiaBH);
		                 	//alert("donGiaYC:"+donGiaYC);
		                 	//alert("donGiaMP:"+donGiaMP);   
		                    return;
		                }
						i=i+1;
		            }
		     }
        }
}

/**


**/
function getPriceKyThuatFromServer(urlAction,myCondition){
  //alert("getPriceKyThuatFromServer"); 
  	try{
		//if (!google.gears.factory || !db) {
		//	return;
	    //}
	//      alert("getPriceKyThuatFromServer2"); 
	var xmlHttp = createXmlHttpRequest();    
	var url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	//alert("myCondition:"+myCondition);
    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	handlerReadyStateChangeForGetPriceKyThuatFromServer(xmlHttp);
    };
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 	
	    
	   
	 }catch(e){
	    alert("getPriceFromServer: error with params: " + urlAction + ","+ myCondition );
	 }    
}

/**

**/
function getDefaultValue(key,textbox){
	
	var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = key;
    
        
//  alert("myCondition:"+myCondition);

    url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI("GetValueDefaultAction") + "&xmlData=" + encodeURI(myCondition);

  //url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;

    xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	var ret =  	handleStateChange_default(xmlHttp);
    	if (ret != null && ret != "") {
    		//alert("ret = " +  ret );
    		if ( ret == "NAM" ) {
    			document.getElementById(prefix_component + textbox).value = "true";
    		} else if ( ret == "NU" ) {
    			document.getElementById(prefix_component + textbox).value = "false";
    		} else {
    			document.getElementById(prefix_component + textbox).value = ret;
    		}
    		
    	}
    };
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.send(params); 
  
}

function handleStateChange_default(xmlHttp1) {
if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  var jsonExpression = "(" + xmlHttp1.responseText + ")";
	//alert(jsonExpression);
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		var i = 0;
		    if(typeof(data.list) == "object"){
		       while (data.list.record[i] != null){
		       		//alert( data.list.record[i]);
		            data1 = data.list.record[i];                
		            if(data1.value != null && data1.value != ""){                	
		                var value = unescape(data1.value);     
		               //alert("value = " + value);              
		                return value;
		            }
					i=i+1;
		       }
		     }
        }
 

}
/*
function getCatalogFromServer(DBTableName,urlAction){

    //alert(DBTableName);
    //alert(urlAction);
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        //alert("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
        //alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
    
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
   
   //alert("url:"+url);
   //alert(url)
    xml = new JKL.ParseXML( url );
    //alert("xml"+xml);
    data = xml.parse();    
    
   // alert("data;" + data);
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i]; 
                //alert("data1.MaSo:"+data1.MaSo);               
                if(data1.MaSo != null){                	
                    var ten;
                   // alert(99991);
                    ten = unescape(data1.Ten); 
                    // alert("ten:"+ten);
                    // alert();           
                    // alert(99992);         
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,""]);                    
                    // alert(i);
                }
				i=i+1;
            }
        }    
}
*/
/*

//duphong
function getCatalogFromServerForDVDP(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
    
    //var url = myContextPath + "/actionServlet";
    
    //var params = "urlAction="+ urlAction + "&xmlData=" + myCondition;
    //xmlHttp.open("POST", url, true);
    //xmlHttp.onreadystatechange = handleStateChange;
   // xmlHttp.setRequestHeader("Content-Type", "text/x-www-form-urlencoded;");
   // xmlHttp.send(params); 
   // xml= xmlHttp.responseXML;
   // alert(xml);
   // data = $.xml2json(xml);
   // alert(data);
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();    
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];                
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);                    
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,"",data1.TuyenMa,data1.ChuongtrinhMa]);                    
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function getCatalogFromServerDiaChi(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close();  
    //var url = myContextPath + "/actionServlet";
    
    //var params = "urlAction="+ urlAction + "&xmlData=" + myCondition;
    //xmlHttp.open("POST", url, true);
    //xmlHttp.onreadystatechange = handleStateChange;
   // xmlHttp.setRequestHeader("Content-Type", "text/x-www-form-urlencoded;");
   // xmlHttp.send(params); 
   // xml= xmlHttp.responseXML;
   // alert(xml);
   // data = $.xml2json(xml);
   // alert(data);
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];
                if(data1.MaSo != null){
                    var ten;
                    ten = unescape(data1.Ten);
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.NgayChinhSua,"",data1.RefMa]);
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function getCatalogFromServerDMT(DBTableName,urlAction){
	
	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){}
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close();  
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    xml = new JKL.ParseXML( url );
    data = xml.parse();
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];
                if(data1.MaSo != null){
                    var ten;
                    ten = unescape(data1.Ten);
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.DonVi,data1.NgayChinhSua,""]);
                    
                }
				i=i+1;
            }
        }
    
}
*/
/*
function sendId_receiveString(idText,urlAction,view_comp_list){
	if (!google.gears.factory || !db) {
        return;
    }
    var myCondition;
    var url;
    var xml;
    var data;
    var myView_comp_list = view_comp_list.split("---");    
    
    if(document.getElementById(idText).value != ""){
    	myCondition = document.getElementById(idText).value;
    	
    	var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
    	xml = new JKL.ParseXML( url );
    	data = xml.parse();
    	i = 0;
    	while (data.list.result[i] != null){
    		data1 = data.list.result[i];
    		if(data1.value != null)
	        	document.getElementById(myView_comp_list[i]).value = data1.value;          
	        i=i+1;
	    }
    }   
}
*/
function sendId_receiveStringForPhieuNhap(idDmt, idKho, urlAction, view_comp_list){
	var myCondition;
    //var url;
    //var xml;
   // var data;
   	var xmlHttp = createXmlHttpRequest();
   	var myView_comp_list = view_comp_list.split("---");
    if(document.getElementById(idDmt).value != "" && document.getElementById(idKho).value){
    	myCondition = document.getElementById(idDmt).value;
    	khoMa = document.getElementById(idKho).value;
    }
    
       
     url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition + "|" + khoMa);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){handleStateChangePN(xmlHttp,myView_comp_list);};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
	 
}
/**

**/
function handleStateChangePN(xmlHttp1,myView_comp_list) {
	if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  	var jsonExpression = "(" + xmlHttp1.responseText + ")";
	//alert(jsonExpression)
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		if ( data.list != null && typeof(data.list) == "object") {
			//alert("bbb");
	    i = 0;
	    	
            while (data.list.result[i] != null){ 
            	//alert("aaa" + data.list.record[i]);           
				data1 = data.list.result[i];
				if(data1.value != null)
	        	document.getElementById(myView_comp_list[i]).value = data1.value;          
	        	i=i+1;
            }
        }
  }
 

}

/**

**/
function getNameById_Catalog(myId,Object){
	//alert("myId: " + myId);
	var arName_Catalog = Object.filter("MaSo = ?", myId).toArray();
	//var rsName_Catalog = db.execute("select * from " + DBTableName + " where MaSo=\'" + myId + "\'");
    var myTen = "";
    //while (rsName_Catalog.isValidRow()) {
	//	myTen = rsName_Catalog.fieldByName("Ten");
	//	rsName_Catalog.next();
	//}
	//rsName_Catalog.close();
	for (var  i = 0; i < arName_Catalog.length; i ++) {
       myTen = arName_Catalog[i].Ten;
    }
    //alert("myTen: " + myTen);
	return myTen;
}

/**

**/
function createTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
			else
			myTable += arr1[i] + "_";
	}
	var mySql = "";
	mySql += "create table if not exists " + myTable + " (";
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i] + " " + MY_TABLE[1][i];
		else
			mySql += MY_TABLE[0][i] + " " + MY_TABLE[1][i] + ", ";
	}
	mySql += ")";
	db.execute(mySql);
}

/**

**/
function insertTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "insert into " + myTable + " (";
       
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i];
		else
			mySql += MY_TABLE[0][i] + ", ";
	}
	
	mySql += ") values(";
	
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];	
		
		if(i == MY_TABLE[0].length - 1){
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\'";
			}
			else {
				mySql += "\'\'";
			}
		}	
		else {
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\',";
			}
			else {
				mySql += "\'\',";
			}
			
		}	
	}
	
    mySql += ")";
    db.execute(mySql);			
}

/**

**/
function updateTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "update " + myTable + " set ";
    
    //alert(MY_TABLE[0].length);   
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];
	
		if(i == MY_TABLE[0].length -1){
			if ( document.getElementById(prefix_component + myComponent) != null) {
				mySql += MY_TABLE[0][i] + "=\'" + document.getElementById(prefix_component + myComponent).value + "\'";
				//alert("in mysql statement1");
			}
			else {
				//mySql += MY_TABLE[0][i] + "=\'\' ";
			}
		}	
		else{
			if (document.getElementById(prefix_component + myComponent) != null ) {
				mySql += MY_TABLE[0][i] + "=\'" + document.getElementById(prefix_component + myComponent).value + "\', ";
			}
			else {
				//mySql += MY_TABLE[0][i] + "=\'\', ";
			}
		}
	}
	//Manh added
	var length = mySql.length;
	//alert(length);
	var tail = mySql.substr(length-2,1);
	//alert(tail);
	if (tail == ",") {
		mySql = mySql.substr(0, length - 2);
		//alert(mySql);
		
	}
	//Manh end
      mySql += " where " + MY_TABLE[0][0] + "=\'" + 
    		   document.getElementById(prefix_component + 
		   "MAPHU").value + "\'";
	
	//	alert(mySql);
      db.execute(mySql);		
}

/**

**/
function deleteTableDB(MY_TABLE){
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	
	db.execute("delete from " + myTable + " where " + MY_TABLE[0][0] + 
			"=\'" + document.getElementById(prefix_component + 
			MY_TABLE[0][0]).value + "\'");
	
	db.execute(mySql);		
}


/**

**/
function getValueCheckOrRadioList(listId){//listId is a selectManyCheckboxId or a selectOneRadioId
	var myArr = [];
   	myArr = document.getElementsByName(listId);
    if(myArr == null || myArr.length == 0){
   		return;
    }
	var myListResult = [];
	var j = 0;
	for(i = 1; i < myArr.length; i++){
		if(myArr[i].checked == true){
			myListResult[j] = myArr[i].value;
			j++;                		
		}     	               	
	}
	
	//myListResult is a array, which has a type: [value1, value2, value3,...];
	if (j == 0){
		return null; 
	}
	else {
		return myListResult;
	}                             
}


/**

**/
function  createXmlDatafromTable(MY_TABLE,rs) { 
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	var xml ="";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	xml += "<" + myTable + " ";
	for(i=0; i<MY_TABLE[0].length; i++){
		myComponent = MY_TABLE[0][i];
		xml += myComponent + "=\'" +  rs.fieldByName(myComponent) + "\' ";	
	}
	xml += ">";
	return xml;
	
}

/**

**/
function getValueCheckOrRadioList(listId){//listId is a selectManyCheckboxId or a selectOneRadioId
	var myArr = [];
   	myArr = document.getElementsByName(listId);
    if(myArr == null || myArr.length == 0){
   		return;
    }
	var myListResult = [];
	var j = 0;
	for(i = 1; i < myArr.length; i++){
		if(myArr[i].checked == true){
			myListResult[j] = myArr[i].value;
			j++;                		
		}     	               	
	}
	
	//myListResult is a array, which has a type: [value1, value2, value3,...];
	if (j == 0){
		return null; 
	}
	else {
		return myListResult;
	}                             
}

/**

**/
function  createXmlDatafromTable1(MY_TABLE, rs1) { 
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	var xml="";
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	xml += "<" + myTable + " ";
	for(i=0; i<MY_TABLE[0].length; i++){
		myComponent = MY_TABLE[0][i];
		xml += myComponent + "=\'" +  rs1.fieldByName(myComponent) + "\' ";	
	}
	xml += "/>";
	return xml;
}

function insertDBTableFrom(MY_TABLE, data1) {
	var arr1 = MY_TABLE[0][0].split('_');
	var myTable = "";
	
	for (i=0; i<arr1.length-1; i++){
		if(i == arr1.length-2)
			myTable += arr1[i];
		else	
			myTable += arr1[i] + "_";	
	}
	
	var mySql = "";
	mySql += "insert into " + myTable + " (";
       
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == MY_TABLE[0].length - 1)
			mySql += MY_TABLE[0][i];
		else
			mySql += MY_TABLE[0][i] + ", ";
	}
	
	mySql += ") values(";
	
	for(i=0; i<MY_TABLE[0].length; i++){
		if(i == 0)
			myComponent = "MAPHU";
		else if(i == 1)	
			myComponent = "MAPHU1";
		else
			myComponent = MY_TABLE[0][i];
			//alert(myComponent);
		if (i == 0 || i == 1){
			if ( document.getElementById(prefix_component + myComponent) != null) {
				mySql += "\'" + document.getElementById(prefix_component + myComponent).value + "\',";
			}
			else {
				mySql += "\'\',";
			}
		}	
		else if(i == MY_TABLE[0].length - 1){
			if (data1[myComponent] != null && data1[myComponent] != "" ) {
				mySql += "\'" + data1[myComponent] + "\'";
			}
			else {
				mySql += "\'\'";
			}
		}	
		else {
			//alert(data1.length);
			//alert(data1.myComponent);
			if (data1[myComponent] != null && data1[myComponent] != "") {
				mySql += "\'" + data1[myComponent] + "\',";
			}
			else {
				mySql += "\'\',";
			}
			
		}	
	}
	
    mySql += ")";
    db.execute(mySql);
}

//Chi begin

function listCatalogJSF_PhieuXuatBh(myCombobox, data){
	var myComboboxId = document.getElementById(myCombobox);
	
	try{
		var search = dijit.byId(myComboboxId.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		
		//alert("data: " + data);
		
		var i = 0;
		if (data != null){
			while (data.record[i] != null){
	          	data1 = data.record[i];   
	          	//alert('data1: ' + data1);
	            if(data1.MaHang != null){
	               	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon +"___" +data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;     
	                CatalogStore.newItem({id: myId, title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});  
	           	}
				i=i+1;
	         }
	         if (i == 0) { // truong hop nay chi co' 1 record
	         	data1 = data.record;   
	          	//alert('data1: ' + data1);
	            if(data1.MaHang != null){
	               	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon +"___" +data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;     
	                CatalogStore.newItem({id: myId, title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});  
	           	}
	         }
	    }
	    else{	   	  
	    	myComboboxId.value = "";
	    }
		search.store = CatalogStore;
	} 
	catch(e){
	   alert("catch .... in....listCatalogJSF_PhieuXuatBh():"+e );
	}
}

function listCatalogSearchForPhieuXuatBh(myCombobox){
	comboboxId = myCombobox.id;
	arr1 = comboboxId.split(":");
    //alert('arr1: ' + arr1);
	comboboxIdJSF = arr1[arr1.length - 1];
	arr2 = comboboxIdJSF.split("__");
	//alert('arr2: ' + arr1);
	myTableName = "";
	if(arr2.length > 1){
		for(i = 0; i < arr2.length - 1; i++){
		    if (i == 0){
		       myTableName += arr2[i];
		    }else{
		     myTableName += "_" + arr2[i]  ;
		    }
		}
	}
	else{
	   myTableName = arr2[0];
	}
	
	try{
		if (dataList == null){
		   return;
		}   		
		var i = 0;
		var data = dataList;
		   
		var myComboboxValue = myCombobox.getDisplayedValue();
		//alert(1); 
		var search = dijit.byId(myCombobox.id);
		var jsonData = { identifier: "id", items: [], label: "title" };
		var CatalogStore = new dojo.data.ItemFileWriteStore( { data: jsonData } );
		CatalogStore.newItem({id: "", title: ""});
		//alert(2);	
		
		while (data.record[i] != null){            	
        	    data1 = data.record[i];   
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                //alert(3);
                if ( myComboboxValue == "" || iIndex == 0 ){
                   // alert(4);
	                if(data1.TonKhoMa != null){     
	                	//alert(5);  
	                  	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon + "___" + data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;      
	                    CatalogStore.newItem({id: myId , title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});
	                    //alert(6);  
	                }
	            }    
				i=i+1;
        }
        if (i == 0) { // truong hop nay chi co' 1 record
        		data1 = data.record;   
                var varTitle  =   data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia; 
                var iIndex = varTitle.indexOf (myComboboxValue);
                //alert(3);
                if ( myComboboxValue == "" || iIndex == 0 ){
                   // alert(4);
	                if(data1.TonKhoMa != null){     
	                	//alert(5);  
	                  	var myId =  data1.TonKhoMa + "___" + data1.TonKhoTon + "___" + data1.MaHang + "___" + data1.TenHang + "___" + data1.QuocGiaSx +  "___" + data1.HangSx + "___" + data1.DonGia;      
	                    CatalogStore.newItem({id: myId , title: data1.MaHang + "  " + data1.TenHang + "  " + data1.QuocGiaSx +  "  " + data1.HangSx + "  " + data1.DonGia});
	                    //alert(6);  
	                }
	            }    
        }
        
		search.store = CatalogStore;
		//alert(7);	
	} 
	catch(e){
	   alert("catch .... in....listCatalogSearchForPhieuXuatBh():"+e );
	}
}

function mySetValueForPhieuXuatBh(comboboxId){
   var myValue = document.getElementById(prefix_component + '__listtonkho_ma_2').value;
   //alert('__listtonkho_ma_2.value:   ' + myValue);
   if (myValue == null || myValue ==""){
      return;
   }
   
   var i = myValue.indexOf("___"); 
   var tonKhoMa = myValue.substring(0,i);   
   myValue = myValue.substring(i+3);
   //alert('tonKhoMa: ' + tonKhoMa);
   document.getElementById(prefix_component + "__tonkhoMa").value = tonKhoMa;
   
   var i = myValue.indexOf("___"); 
   var tonkhoTon = myValue.substring(0,i);   
   //alert('tonkhoTon: ' + tonkhoTon);
   document.getElementById(prefix_component + "__tonkho").value = tonkhoTon;
   myValue = myValue.substring(i+3);
   
   
   var i = myValue.indexOf("___"); 
   var maHang = myValue.substring(0,i);   
   //document.getElementById(prefix_component + "__maHang").value = maHang;
   myValue = myValue.substring(i+3);
   //myOnblurTextboxForDmThuoc(this.id,'DM_THUOC','','');
    
   var i = myValue.indexOf("___"); 
   var tenHang = myValue.substring(0,i);   
   myValue = myValue.substring(i+3);
   //alert('tenHang: ' + tenHang);
   i = myValue.indexOf("___"); 
   var quocGiaSx = myValue.substring(0,i);
   document.getElementById(prefix_component + "__nsx").value = quocGiaSx;
   myValue = myValue.substring(i+3);
   //alert('quocGiaSx: ' + quocGiaSx);
   i = myValue.indexOf("___"); 
   var hangSx = myValue.substring(0,i);
   document.getElementById(prefix_component + "__hsx").value = hangSx;
   myValue = myValue.substring(i+3);
   //alert('hangSx: ' + hangSx);
   
   //i = myValue.indexOf("___"); 
  	var dongia = myValue;
  	document.getElementById(prefix_component + "__dongia").value = dongia;
  // myValue = myValue.substring(i+3);
   //alert('dongia: ' + dongia);
   document.getElementById(prefix_component +'__xuat').focus();
}

function setAttrForComboboxJSFForPhieuXuatBh(_mySpan,comboboxId,pageSize){
  try{
	var mySpan = document.getElementById(_mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	
	myComboboxId.invalidMessage="";
	myComboboxId.onchange=" dojo.byId( prefix_component + \'__listtonkho_ma_2\').alt=arguments[0]";
	myComboboxId.onblur=" assignAltToValue(prefix_component + \'__listtonkho_ma_2\'); mySetValueForPhieuXuatBh(\'"+ comboboxId + "\')";
	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";
	myComboboxId.autoComplete=false;	
	myComboboxId.pageSize=pageSize;
  }
  catch(e){
    alert("error at setAttrForComboboxJSFForPhieuXuatBh :" +  e);
  }
}

//Chi end
//Manh added
function myOnblurTextbox_DoiTuong(textboxId,comboboxId,textboxId2,hiddenBox){
  try{
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	mycombobox = document.getElementById(comboboxId);
	mytextbox2 = document.getElementById(textboxId2);
	hiddenbox = document.getElementById(hiddenBox);
	if(textboxValue != ""){
		
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("MaSo like \'" + textboxValue + "\'").toArray();
		for (var i = 0; i < objArr.length; i++) {
			if (objArr[i].Ten != null && objArr[i].Ten != ""){
	        	 mycombobox.value = objArr[i].Ten;
	        	 //alert("TyLeMien: " + rs.fieldByName("TyLeMien"));
	        	 if (objArr[i].TyLeMien != null && objArr[i].TyLeMien != "") {
	        	 	//alert(0);
	        	 	var value = 100 - parseInt(objArr[i].TyLeMien);
	        	 	//alert("value: " + value );
	        	 	mytextbox2.value = value;
	        	 	hiddenbox.value = objArr[i].TyLeMien;
	        	 }
	        	 havingData = true;
	        }
		}
		
		/*
		var rs = db.execute("select * from " + myTableName + " where MaSo like \'" + textboxValue + "\'");
		var havingData = false;
	    while (rs.isValidRow()) {
	        if (rs.fieldByName("Ten") != null && rs.fieldByName("Ten") != ""){
	        	 mycombobox.value = rs.fieldByName("Ten");
	        	 //alert("TyLeMien: " + rs.fieldByName("TyLeMien"));
	        	 if (rs.fieldByName("TyLeMien") != null && rs.fieldByName("TyLeMien") != "") {
	        	 	//alert(0);
	        	 	var value = 100 - parseInt(rs.fieldByName("TyLeMien"));
	        	 	//alert("value: " + value );
	        	 	mytextbox2.value = value;
	        	 	hiddenbox.value = rs.fieldByName("TyLeMien");
	        	 }
	        	 havingData = true;
	        }
	       
	        //alert(rs.fieldByName("Ten"));
	        
	        //mycombobox.select();
	        //  alert(1);
	        //return;
	        rs.next();
	    }
	    */
	    if (havingData == true){
	       //alert("mycombobox.value__true:"+mycombobox.value);
	       return;
	    }
	    //alert("mycombobox.value:"+mycombobox.value);
	    //alert("----------------");
	    /*
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    	hiddenbox.value = "";
	    }
	    */
	     //alert(3);
	    //rs.close();
	    //  alert(4);
	    mycombobox.value = "";   
	    //alert("mycombobox.value:"+mycombobox.value);
	    mytextbox.value = "";
	    hiddenbox.value = "";
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
    }else{
    	mycombobox.value = ""; 
    	hiddenbox.value = "";
    }
  }catch(e){
    alert("myOnblurTextbox():"+e);
  }  
      
}
//Manh end
//Manh added

//Manh end
// Manh added
function setAttrForCombobox_DoiTuong(textboxId,mySpan,comboboxId,pageSize,textboxId2,hiddenBox){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_DoiTuong(\'" + textboxId + "\',\'" + comboboxId + "\',\'"+ textboxId2+ "\',\'"+ hiddenBox + "\'); assignAltToValue(\'" + textboxId + "\')";


	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
}
// Manh end
//Manh added
function myOnblurCombobox_DoiTuong(textboxId,comboboxId,textboxId2,hiddenBox){
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	var tyletra = document.getElementById(hiddenBox);
	if(comboboxValue != ""){
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		mytextbox = document.getElementById(textboxId);
		mytextbox2 = document.getElementById(textboxId2);
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("Ten like \"" + comboboxValue + "\"").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
	        if (objArr[i].TyLeMien != null && objArr[i].TyLeMien != "") {
	        	var value = 100 - parseInt(objArr[i].TyLeMien);
	        	 	//alert("value: " + value );
	       		mytextbox2.value = value;
	       		tyletra.value = objArr[i].TyLeMien;
	       	}
	       	havingData = true;
		}
		
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	        if (rs.fieldByName("TyLeMien") != null && rs.fieldByName("TyLeMien") != "") {
	        	var value = 100 - parseInt(rs.fieldByName("TyLeMien"));
	        	 	//alert("value: " + value );
	       		mytextbox2.value = value;
	       		hiddenbox.value = rs.fieldByName("TyLeMien");
	       	}
	        return;
	        rs.next();
	    }
	    */
	    
	    if(havingData == true){
	    	return;
	    }
	    mytextbox.alt = "";
	    mytextbox2.value = "";
	    tyletra.value = "";
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    //rs.close();	    
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	mytextbox2.value = "";
    	tyletra.value = "";
    }
      
} 
//Manh end
//Manh added
/*
function getCatalogFromServer_TienKham(DBTableName,urlAction){

	if (!google.gears.factory || !db) {
		
        return;
    }
    var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	//createXmlHttpRequest();
    myCondition = 0;
    try{
        rs = db.execute("select NgayChinhSua from "+ DBTableName +" order by NgayChinhSua desc limit 1");        
    }
    catch(e){
        //alert(e);
    }
     
    while (rs.isValidRow()) {
        myCondition = rs.field(0);
        rs.next();
    }
    rs.close(); 
   var url = myContextPath + "/actionServlet?urlAction=" + urlAction + "&xmlData=" + myCondition;
   
    xml = new JKL.ParseXML( url );
    data = xml.parse(); 
    i = 0;
            if(typeof(data.list) == "object"){
            while (data.list.record[i] != null){
                data1 = data.list.record[i];               
                if(data1.MaSo != null){                	
                    var ten;
                    ten = unescape(data1.Ten);       
                    db.execute("insert into " + DBTableName + " values(?,?,?,?,?,?,?)", ["","",data1.MaSo,ten,data1.DonGia,data1.NgayChinhSua,""]); 
                }
				i=i+1;
            }
        }
    
}
*/
//Manh end
//Manh added
function myOnblurTextbox_TienKham(textboxId,comboboxId,textboxId2,textboxId3){
  try{
	mytextbox = document.getElementById(textboxId);
	textboxValue = mytextbox.value;
	mycombobox = document.getElementById(comboboxId);
	mytextbox2 = document.getElementById(textboxId2);
	mytextbox3 = document.getElementById(textboxId3);
	if(textboxValue != ""){
		
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("MaSo like \'" + textboxValue + "\'").toArray();
		
		for (var i = 0; i < objArr.length; i++) {
		    
			if (objArr[i].Ten != null && objArr[i].Ten != ""){
	        	 mycombobox.value = objArr[i].Ten;
	        	
	        	 if (objArr[i].DonGia != null) {
	        	 	//alert(0);
	        	 	if(mytextbox3.value != null && mytextbox3.value != "") {
	        	 		var value = objArr[i].DonGia * parseInt(mytextbox3.value)* 0.01;
	        	 	//alert("value: " + value );
	        	 		mytextbox2.value = value;
	        	 	}
	        	 	else {
	        	 		mytextbox2.value = objArr[i].DonGia;
	        	 	}
	        	 }
	        	 havingData = true;
	        }
		}
		
		
	    if (havingData == true){
	       //alert("mycombobox.value__true:"+mycombobox.value);
	       return;
	    }
	   
	    mytextbox.alt = "";
	     //alert(3);
	    //rs.close();
	    //  alert(4);
	    mycombobox.value = "";   
	    //alert("mycombobox.value:"+mycombobox.value);
	    mytextbox.value = "";
	    //alert(6);
	    mytextbox.focus();
	    //alert(4);
    }else{
    	mycombobox.value = ""; 
    }
  }catch(e){
    alert("myOnblurTextbox_TienKham():"+e.description);
  }  
      
}
//Manh end
// Manh added
function setAttrForCombobox_TienKham(textboxId,mySpan,comboboxId,pageSize,textboxId2,textboxId3){
	var mySpan = document.getElementById(mySpan);
	mySpan.dojoType="dojo.data.ItemFileReadStore"; 
	mySpan.jsId="defaultStore";
	mySpan.url="#{facesContext.externalContext.requestContextPath}/resources/gears/CatalogStore.txt";
	
	var myComboboxId = document.getElementById(comboboxId);
	myComboboxId.dojoType="dijit.form.FilteringSelect";
	myComboboxId.invalidMessage="";
	myComboboxId.onchange="dojo.byId(\'" + textboxId + "\').alt=arguments[0]";
	myComboboxId.onblur="myOnblurCombobox_TienKham(\'" + textboxId + "\',\'" + comboboxId + "\',\'"+ textboxId2+ "\',\'"+ textboxId3+ "\'); assignAltToValue(\'" + textboxId + "\')";


	//myComboboxId.store="defaultStore";
	myComboboxId.searchAttr="title";	
	myComboboxId.pageSize=pageSize;
	myComboboxId.autoComplete=false;
}
// Manh end
//Manh added
function myOnblurCombobox_TienKham(textboxId,comboboxId,textboxId2,textboxId3){
	mycombobox = document.getElementById(comboboxId);
	comboboxValue = mycombobox.value;
	
	mytextbox = document.getElementById(textboxId);
	mytextbox2 = document.getElementById(textboxId2);
	mytextbox3 = document.getElementById(textboxId3);
	if(comboboxValue != ""){
		var arr1 = comboboxId.split("__");
		var myTableName = "";
		//alert(comboboxIdJSF);
		if(arr1.length > 1){
			for(i = 0; i < arr1.length - 1; i++){
			    if (i == 0){
			       myTableName += arr1[i];
			    }else{
			    	 myTableName += "_" + arr1[i]  ;
			    }
				
			}
		}else{
		   myTableName = arr1[0];
		}
		
		
		var Object = getObject(myTableName);
		var havingData = false;
		var objArr = Object.filter("Ten like \"" + comboboxValue + "\"").toArray();
		for (var i = 0; i < objArr.length; i++) {
			mytextbox.alt = objArr[i].MaSo;
	         if (objArr[i].DonGia != null) {
	        	 	//alert(0);
	        	 	if(mytextbox3.value != null && mytextbox3.value != "") {
	        	 		var value = objArr[i].DonGia * parseInt(mytextbox3.value)* 0.01;
	        	 	//alert("value: " + value );
	        	 		mytextbox2.value = value;
	        	 	}
	        	 	else {
	        	 		mytextbox2.value = objArr[i].DonGia;
	        	 	}
	        	 }
	        	 havingData = true;
		}
		
		/*
		var rs = db.execute("select * from " + myTableName + " where Ten like \"" + comboboxValue + "\"");
	    while (rs.isValidRow()) {
	        mytextbox.alt = rs.fieldByName("MaSo");
	         if (rs.fieldByName("DonGia") != null) {
	        	 	//alert(0);
	        	 	if(mytextbox3.value != null && mytextbox3.value != "") {
	        	 		var value = rs.fieldByName("DonGia") * parseInt(mytextbox3.value)* 0.01;
	        	 	//alert("value: " + value );
	        	 		mytextbox2.value = value;
	        	 	}
	        	 	else {
	        	 		mytextbox2.value = rs.fieldByName("DonGia");
	        	 	}
	        	 }
	        return;
	        rs.next();
	    }
	    */
	    /*
	    if(rs.isValidRow() == false){
	    	mytextbox.alt = "";
	    	mytextbox2.value = "";
	    }	
	    */
	    if (havingData == true) {
	    	return;
	    }
	    mytextbox.alt = "";
	    mytextbox2.value = "";
	    mycombobox.focus();
	    mycombobox.value = "";	
	    
	    //rs.close();	    
    }
    else {
    	//alert(1000);
    	mytextbox.alt = "";
    	mytextbox2.value = "";
    }
      
} 
// Manh chuan hoa theo anh bao 
function getDefaultValue(key,textbox){
	//alert(1);
	var rs;
    var myCondition;
    var url;
    var xml;
    var data;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = key;
    
    url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI("GetValueDefaultAction") + "&xmlData=" + encodeURI(myCondition);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){
    	var ret =  	handleStateChange_default(xmlHttp);
    	if (ret != null && ret != "") {
    		//alert("ret = " +  ret );
    		if ( ret == "NAM" ) {
    			document.getElementById(prefix_component + textbox).value = "true";
    		} else if ( ret == "NU" ) {
    			document.getElementById(prefix_component + textbox).value = "false";
    		} else {
    			document.getElementById(prefix_component + textbox).value = ret;
    		}
    		
    	}
    	};
	xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xmlHttp.send(params); 
  
}

function handleStateChange_default(xmlHttp1) {
if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  var jsonExpression = "(" + xmlHttp1.responseText + ")";
	var data = eval(jsonExpression);
		var i = 0;
		    if(typeof(data.list) == "object"){
		       while (data.list.record[i] != null){
		       		data1 = data.list.record[i];                
		            if(data1.value != null && data1.value != ""){                	
		               var value = unescape(data1.value);     
		               return value;
		            }
					i=i+1;
		       }
		     }
        }
 

}


// Manh chuan hoa theo anh bao 
function getCatalogFromServer_new(DBTableName,urlAction,Object){

   // var rs;
    var myCondition;
    //var url;
    //var xml;
   // var data;
   	var xmlHttp = createXmlHttpRequest();
    myCondition = 0;
    
    try {
    	var max =  Object.all().aggregateFuncSql("max(NgayChinhSua)");
    	
    	if ( max != null ) {
    		//alert(max);
    		myCondition = max;
    	}
    } catch (e) {
    	alert( "eeeeeeee:" + e.description ) ;
    }
    
     url = myContextPath + "/actionServlet?";
    
    var params = "urlAction="+ encodeURI(urlAction) + "&xmlData=" + encodeURI(myCondition);
	xmlHttp.open("POST", url, false);

    xmlHttp.onreadystatechange = function(){handleStateChange(xmlHttp,DBTableName);};
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlHttp.send(params); 
     
}

function handleStateChange(xmlHttp1,DBTableName) {
if (xmlHttp1.readyState == 4 && xmlHttp1.status == 200){
	

  var jsonExpression = "(" + xmlHttp1.responseText + ")";
//alert(jsonExpression)
	var data = eval(jsonExpression);
		//alert("aaa" + data.list);
		if ( data.list != null && typeof(data.list) == "object") {
			//alert("bbb");
	    i = 0;
	    	
            while (data.list.record[i] != null){ 
            	//alert("aaa" + data.list.record[i]);           
				data1 = data.list.record[i];
				if ( DBTableName == "DT_DM_DOI_TUONG" ) {
					DtDmDoiTuong.transaction(function(){				
						new DtDmDoiTuong(data1).save();
					} );	
				} else if (DBTableName == "DT_DM_NHAN_VIEN") {
					DtDmNhanVien.transaction(function(){				
					new DtDmNhanVien(data1).save();});
				} else if ( DBTableName == "DM_DM_CLS") {
					DtDmCls.transaction(function(){				
					new DtDmCls(data1).save();});
				} else if ( DBTableName == "DT_DM_BAN_KHAM") {
					DtDmBanKham.transaction(function(){				
					new DtDmBanKham(data1).save();});
				} else if ( DBTableName == "DM_KHOA") {
					DmKhoa.transaction(function(){				
					new DmKhoa(data1).save();});
				} else if ( DBTableName == "DT_DM_KY_THUAT" ) {
					DtDmKyThuat.transaction(function(){				
					new DtDmKyThuat(data1).save();} );	
				} else if ( DBTableName == "DM_DAN_TOC" ) {
					DmDanToc.transaction(function(){				
					new DmDanToc(data1).save();} );	
				} else if ( DBTableName == "DM_TINH" ) {
					DmTinh.transaction(function(){				
					new DmTinh(data1).save();} );	
				} else if ( DBTableName == "DM_HUYEN" ) {
					DmHuyen.transaction(function(){				
					new DmHuyen(data1).save();} );	
				} else if ( DBTableName == "DM_XA" ) {
					DmXa.transaction(function(){				
					new DmXa(data1).save();} );	
				} else if ( DBTableName == "DM_NGHE_NGHIEP" ) {
					DmNgheNghiep.transaction(function(){				
					new DmNgheNghiep(data1).save();} );	
				}
				else if ( DBTableName == "DM_BENH_VIEN" ) {
					DmBenhVien.transaction(function(){				
					new DmBenhVien(data1).save();} );	
				}
				else if ( DBTableName == "DM_BENH_ICD" ) {
					DmBenhIcd.transaction(function(){				
					new DmBenhIcd(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_KCB_BHYT" ) {
					DtDmKcbBhyt.transaction(function(){				
					new DtDmKcbBhyt(data1).save();} );	
				}
				else if ( DBTableName == "DM_PHUONG_TIEN" ) {
					DmPhuongTien.transaction(function(){				
					new DmPhuongTien(data1).save();} );	
				}
				else if ( DBTableName == "DM_PL_TAI_NAN" ) {
					DmPlTaiNan.transaction(function(){				
					new DmPlTaiNan(data1).save();} );	
				}
				else if ( DBTableName == "DM_DIEU_TRI" ) {
					DmDieuTri.transaction(function(){				
					new DmDieuTri(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_KET_QUA" ) {
					DtDmKetQua.transaction(function(){				
					new DtDmKetQua(data1).save();} );	
				} else if ( DBTableName == "DT_DM_HUONG_DT" ) {
					DtDmHuongDt.transaction(function(){				
					new DtDmHuongDt(data1).save();} );	
				} else if ( DBTableName == "DT_DM_NOI_SINH" ) {
					DtDmNoiSinh.transaction(function(){				
					new DtDmNoiSinh(data1).save();} );	
				} else if ( DBTableName == "DM_LY_DO_CV" ) {
					DmLyDoCv.transaction(function(){				
					new DmLyDoCv(data1).save();} );	
				} else if ( DBTableName == "DM_DIA_DIEM" ) {
					DmDiaDiem.transaction(function(){				
					new DmDiaDiem(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_PHAU_THUAT" ) {
					DtDmPhauThuat.transaction(function(){				
					new DtDmPhauThuat(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_VO_CAM" ) {
					DtDmVoCam.transaction(function(){				
					new DtDmVoCam(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_TINH_BHYT" ) {
					DtDmTinhBhyt.transaction(function(){				
					new DtDmTinhBhyt(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_KHOI_BHYT" ) {
					DtDmKhoiBhyt.transaction(function(){				
					new DtDmKhoiBhyt(data1).save();} );	
				}
				else if ( DBTableName == "DM_THUOC" ) {
					DmThuoc.transaction(function(){				
					new DmThuoc(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_GIA_PHONG" ) {
					DtDmGiaPhong.transaction(function(){				
					new DtDmGiaPhong(data1).save();} );	
				}
				else if ( DBTableName == "DM_KHO" ) {
					DmKho.transaction(function(){				
					new DmKho(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_NGUON" ) {
				
					DtDmNguon.transaction(function(){				
					new DtDmNguon(data1).save();} );	
				}
				else if ( DBTableName == "DM_KINH_PHI" ) {
					DmKinhPhi.transaction(function(){				
					new DmKinhPhi(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_LOAI" ) {
					DtDmLoai.transaction(function(){				
					new DtDmLoai(data1).save();} );	
				}
				else if ( DBTableName == "DM_PHAN_LOAI" ) {
					DmPhanLoai.transaction(function(){				
					new DmPhanLoai(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_PB_CLS" ) {
					DtDmPbCls.transaction(function(){				
					new DtDmPbCls(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_NOI_BAN" ) {
					DtDmNoiBan.transaction(function(){				
					new DtDmNoiBan(data1).save();} );	
				}
				else if ( DBTableName == "DM_QUOC_GIA" ) {
					DmQuocGia.transaction(function(){				
					new DmQuocGia(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_HANG_SX" ) {
					DtDmHangSx.transaction(function(){				
					new DtDmHangSx(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_DVT" ) {
					DtDmDvt.transaction(function(){				
					new DtDmDvt(data1).save();} );	
				}
				else if ( DBTableName == "DT_DM_TIEN_KHAM" ) {
					DtDmTienKham.transaction(function(){				
					new DtDmTienKham(data1).save();} );	
				}
				else if ( DBTableName == "DM_LOAI_BAO_CAO_HSBA_DANG_CN" ) {
					DmLoaiBaoCaoHSBADangCN.transaction(function(){				
					new DmLoaiBaoCaoHSBADangCN(data1).save();} );	
				}
				i=i+1;
            }
        	}
        }
 

}

function insertObjectFromData(Object, fields, values) {
	var jsonData = "({";
	for (var i = 0; i < fields.length; i++) {
		if (i == 0) {
			jsonData += "'" + fields[i] + "':'" + values[i] + "'" ;
		} else {
			jsonData += ", '" + fields[i] + "':'" + values[i] + "'" ;
		}
	}
	jsonData += ")}";
	var obj = new Object(eval(jsonData));
	Object.transaction(function() {
		obj.save();
	});
}

// Lấy thông tin từ giao diện insert xuống database local
function insertObject(Object,arrayNames,arrayValues){
	
	//alert(Object);
	var fieldNames = Object.getFieldNames();
	//alert(fieldNames);
	var array = "({"  ;
	for(var i=0;i<fieldNames.length;i++){
		if ( document.getElementById(prefix_component + fieldNames[i]) != null && document.getElementById(prefix_component + fieldNames[i]).value != "") {
			if( array == "({" ) {
				array +=  "\"" + fieldNames[i] + "\"" + ": \"" + document.getElementById(prefix_component + fieldNames[i]).value + "\"" ;
			}
			else {
				array +=  ", \"" + fieldNames[i] + "\"" + ": \"" + document.getElementById(prefix_component + fieldNames[i]).value  + "\"";
			}
		}
		
	}
	
	if ( arrayNames != null ) {
		//alert("arrayNames.length : " + arrayNames.length);
		for (var j = 0; j < arrayNames.length ; j++) {
			if ( arrayValues[j] != null ) {
				array += ", \"" + arrayNames[j] + "\"" + ": \"" +  arrayValues[j] + "\"";
			}
			else {
				//object[arrayNames[j]] = null ;
			}
		}
	}
	array += "})";
	//alert("array :" + array);
	var data = eval(array);
	var object = new Object( data ) ;
	//object.PHIEU_NHAP_KHO_MAPHU = MAPHU_hidden;
	//alert(20000);
	Object.transaction(function(){				
					object.save();} );
	
	//alert(20001);
}
// Ham update 
function updateObject(Object,fieldName,values){
	 var objectarr =   Object.filter(fieldName + " = ?", values).toArray();
	 
     if ( objectarr != null &&  objectarr.length > 0 ) {
     	var object = objectarr[0];
     	
     	var fieldNames = Object.getFieldNames();
     	for(i=0;i<fieldNames.length;i++){
     		if ( document.getElementById(prefix_component + fieldNames[i]) != null) {
     			//alert(fieldNames[i] + " : " + document.getElementById(prefix_component + fieldNames[i]).value);
     			object[fieldNames[i]] = document.getElementById(prefix_component + fieldNames[i]).value;
				
			}
     	}
     	Object.transaction(function(){
				object.save();
			});
     }
}

function addMonthsToDate(startDate, numMonths) {
    var addYears = Math.floor(numMonths/12);
    var addMonths = numMonths - (addYears * 12);
    var newMonth = startDate.getMonth() + addMonths;
    if (startDate.getMonth() + addMonths > 11) {
      ++addYears;
      newMonth = startDate.getMonth() + addMonths - 12;
    }
    var newDate = new Date(startDate.getYear()+addYears,newMonth,startDate.getDate(),startDate.getHours(),startDate.getMinutes(),startDate.getSeconds());

    // adjust to correct month
    while (newDate.getMonth() != newMonth) {
      newDate = addDaysToDate(newDate, -1);
    }

    return newDate;
}
function addDaysToDate(myDate,days) {
    return new Date(myDate.getTime() + days*24*60*60*1000);
}

function set_ngaysinh(tuoiId,ngaysinhId){
  try{
	tuoi = document.getElementById(tuoiId).value;
	//alert(1);
	if ( tuoi == ""){
		return;
	}
	if(tuoi == 0 || tuoi > 150){
		document.getElementById(tuoiId).value = '';
		document.getElementById(tuoiId).focus();
		return;
	}
	else {
	
	    var donViTuoi = getDonViTuoi();
	    if (donViTuoi == "1"){ // nam tuoi
	    	//alert(1);
	    	document.getElementById(ngaysinhId).value ="01/01/" + ( new Date().getFullYear() - parseInt(tuoi));
	    }else if (donViTuoi == "2"){ //thang tuoi
	    	//alert(2);
	    	var today = new Date();
	        var newDate = addMonthsToDate(today,-1*parseInt(tuoi));
	        
	        var _year = newDate.getFullYear(); 
			var _month = newDate.getMonth() + 1; 
			if (_month.toString().length == 1){
	           _month = "0" + _month;
	        }
			var _day = "01"; 
	        
	        document.getElementById(ngaysinhId).value =_day + "/" + _month + "/" + _year;
	         
	    }else if (donViTuoi == "3"){ //ngay tuoi
	    	var today = new Date();
	       
	        var newDate = addDaysToDate(today,-1*parseInt(tuoi));
	        
	        var _year = newDate.getFullYear(); 
			var _month = newDate.getMonth() + 1; 
			if (_month.toString().length == 1){
	           _month = "0" + _month;
	        }
			var _day = newDate.getDate() ;
	        if (_day.toString().length == 1){
	           _day = "0" + _day;
	        }
	        document.getElementById(ngaysinhId).value = _day + "/" + _month + "/" + _year;
	    
	    }
		
	}
  }catch(e){
    alert("error at set_ngaysinh():"+e.description);
  }	
}

function getDonViTuoi(){
    
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:0").checked == true ){
       return 1;
    }
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:1").checked == true ){
       return 2;
    }
    if ( document.getElementById(prefix_component + "BENHNHAN_DONVITUOI_TEMP:2").checked == true ){
       return 3;
	}
}
/**
 * 
**/
function set_tuoi(tuoiId,ngaysinhId,donvituoiId){
	ngaysinh = document.getElementById(ngaysinhId).value;
	//alert(ngaysinh);
	if(ngaysinh == ""){
		return;
	}	
	else {
		document.getElementById(tuoiId).value = new Date().getFullYear() - parseInt( ngaysinh.substr(6,4)) ;
		
	}
	
	//
	try{
		var myTuoi = parseInt(document.getElementById(tuoiId).value);
		if (myTuoi <= 0){
			//
			//Set 1 day in milliseconds
			var one_day=1000*60*60*24;
			
			var _year = parseInt( ngaysinh.substr(6,4)); //  11/12/2008
			var _month = parseInt( ngaysinh.substr(3,2)); 
			var _day = parseInt( ngaysinh.substr(0,2)); 
			//alert();
			var myBirthday = new Date();
			myBirthday.setFullYear(_year,_month - 1,_day);
			//alert(myBirthday);
			var daybetween = Math.ceil((new Date().getTime() - myBirthday.getTime())/(one_day));
			document.getElementById(tuoiId).value = daybetween;
			
			document.getElementById(donvituoiId + ":2").checked = true;
		}else{
			document.getElementById(donvituoiId + ":0").checked = true;
		}
	
	}catch(e){
	
	   
	    alert("error at set_tuoi()");
	
	}
	
}

function isNullValue(val) {
	if (val == null || val == "null" || val == "NULL") {
		return true;
	} 
	return false;
}

init();

